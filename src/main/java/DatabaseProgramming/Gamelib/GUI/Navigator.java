package DatabaseProgramming.Gamelib.GUI;

import java.io.IOException;
import java.sql.SQLException;
import DatabaseProgramming.Gamelib.Model.UserGame;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Navigator implements INavigator {

	private BorderPane root;

	@Override
	public void setRoot(BorderPane root) {
		this.root = root;
	}

	@Override
	public void show(Node view) {
		root.setCenter(view);
	}

	@Override
	public void showMyGamesList() {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/layout/MyGamesView.fxml")
			);

			VBox view = loader.load();
			show(view);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void showAllGamesList() {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/layout/AllGamesView.fxml")
			);

			VBox view = loader.load();
			show(view);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showGameForm(Object gameData) throws SQLException {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/layout/GameView.fxml")
			);

			VBox view = loader.load();

			GameController controller = loader.getController();
			controller.initializeView(gameData);

			show(view);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}