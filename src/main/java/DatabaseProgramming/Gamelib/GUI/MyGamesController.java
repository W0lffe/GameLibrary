package DatabaseProgramming.Gamelib.GUI;

import java.sql.SQLException;
import DatabaseProgramming.Gamelib.App;
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


public class MyGamesController {
	
	@FXML
	private TableView<UserGameView> gameTable;
	
	private TableColumn<UserGameView, Integer> idColumn;
	
	private TableColumn<UserGameView, String> nameColumn;
	
	private TableColumn<UserGameView, Integer> playtimeColumn;
	
	private TableColumn<UserGameView, Integer> ratingColumn;
	
	private TableColumn<UserGameView, String> developerColumn;
	
	private TableColumn<UserGameView, String> genreColumn;
	
	private TableColumn<UserGameView, Integer> releasedColumn;

	private TableColumn<UserGameView, Void> editColumn;
	
	private TableColumn<UserGameView, Void> deleteColumn;

	private ObservableList<UserGameView> gameList;
	
	private GameService gameService;

	@SuppressWarnings({ "deprecation", "unchecked" })
	@FXML
	public void initialize() throws SQLException {
		gameTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		idColumn = new TableColumn<>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idColumn.getStyleClass().add("table-column");

		nameColumn = new TableColumn<>("Game");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("gameName"));
		nameColumn.getStyleClass().add("table-column");

		genreColumn = new TableColumn<>("Genre");
		genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
		genreColumn.getStyleClass().add("table-column");

		releasedColumn = new TableColumn<>("Release Year");
		releasedColumn.setCellValueFactory(new PropertyValueFactory<>("released"));
		releasedColumn.getStyleClass().add("table-column");

		playtimeColumn = new TableColumn<>("Hours Played");
		playtimeColumn.setCellValueFactory(new PropertyValueFactory<>("playtime"));
		playtimeColumn.getStyleClass().add("table-column");

		ratingColumn = new TableColumn<>("Rating");
		ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
		ratingColumn.getStyleClass().add("table-column");

		developerColumn = new TableColumn<>("Developer");
		developerColumn.setCellValueFactory(new PropertyValueFactory<>("developer"));
		developerColumn.getStyleClass().add("table-column");
		
		setupEditColumn();
		setupDeleteColumn();

		gameService = App.getGameService();
		gameList = FXCollections.observableArrayList(
				gameService.getAllGamesByUser( App.getUser() )
		);
	
		gameTable.getColumns().addAll(idColumn, nameColumn, genreColumn, releasedColumn, playtimeColumn, ratingColumn,
				developerColumn, editColumn, deleteColumn);
		
		gameTable.setItems(gameList);

	}

	private void setupEditColumn() {

		editColumn = new TableColumn<>("Edit");

		editColumn.setCellFactory(col -> new TableCell<UserGameView, Void>() {

			private final Button editBtn = new Button("🛠");

			{
				editBtn.setOnAction(e -> {
					UserGameView userGameView = getTableView().getItems().get(getIndex());
					
					 try {
						 
						 	UserGame userGame = gameService.getSpecificUserGame( userGameView.getId() );
						 	App.getNavigator().showGameForm(userGame);  

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
					setGraphic(editBtn);
				}
			}
		});

	}
	
	private void setupDeleteColumn() {

		deleteColumn = new TableColumn<>("Delete");

		deleteColumn.setCellFactory(col -> new TableCell<UserGameView, Void>() {

			private final Button delBtn = new Button("🗑");

			{
				delBtn.setOnAction(e -> {
					UserGameView userGameView = getTableView().getItems().get(getIndex());
					
					try {
						UserGame userGame = gameService.getSpecificUserGame( userGameView.getId() );
						gameService.removeUserGame( userGame );
						gameTable.getColumns().clear();
						initialize();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);

				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(delBtn);
				}
			}
		});

	}

}
