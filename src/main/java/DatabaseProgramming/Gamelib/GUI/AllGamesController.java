package DatabaseProgramming.Gamelib.GUI;

import java.sql.SQLException;

import DatabaseProgramming.Gamelib.App;
import DatabaseProgramming.Gamelib.Model.Game;
import DatabaseProgramming.Gamelib.Model.GameView;
import DatabaseProgramming.Gamelib.Model.UserGame;
import DatabaseProgramming.Gamelib.Model.UserGameView;
import DatabaseProgramming.Gamelib.Service.GameService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AllGamesController {

	@FXML
	private TableView<GameView> gameTable;
	@FXML
	private TableColumn<GameView, Integer> idColumn;
	@FXML
	private TableColumn<GameView, String> titleColumn;;
	@FXML
	private TableColumn<GameView, String> developerColumn;
	@FXML
	private TableColumn<GameView, String> releasedColumn;
	@FXML
	private TableColumn<GameView, String> genreColumn;
	
	private TableColumn<GameView, Void> addColumn;;

	private ObservableList<GameView> gameList;

	@SuppressWarnings("deprecation")
	@FXML
	public void initialize() throws SQLException {
		gameTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		GameService gameService = App.getGameService();
		gameList = FXCollections.observableArrayList(gameService.getAllGames());

		idColumn = new TableColumn<>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idColumn.getStyleClass().add("table-column");

		titleColumn = new TableColumn<>("Game");
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		titleColumn.getStyleClass().add("table-column");

		developerColumn = new TableColumn<>("Developer");
		developerColumn.setCellValueFactory(new PropertyValueFactory<>("developer"));
		developerColumn.getStyleClass().add("table-column");

		releasedColumn = new TableColumn<>("Released");
		releasedColumn.setCellValueFactory(new PropertyValueFactory<>("release_year"));
		releasedColumn.getStyleClass().add("table-column");

		genreColumn = new TableColumn<>("Genre");
		genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
		genreColumn.getStyleClass().add("table-column");
		
		setupAddColumn();

		gameTable.getColumns().addAll(idColumn, titleColumn, developerColumn, releasedColumn, genreColumn, addColumn);
		
		gameTable.setItems(gameList);

	}
	
	private void setupAddColumn() {

		addColumn = new TableColumn<>("Add");

		addColumn.setCellFactory(col -> new TableCell<GameView, Void>() {

			private final Button addBtn = new Button("➕");

			{
				addBtn.setOnAction(e -> {
					GameView gameView = getTableView().getItems().get(getIndex());
					
					 try {
						 	GameService gameService = App.getGameService();
						 	Game game = gameService.getSpecificGameById( gameView.getId() );
						 	App.getNavigator().showGameForm( game );  

					    } catch (SQLException ex) {
					        ex.printStackTrace();
					    }
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);

				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(addBtn);
				}
			}
		});

	}

}
