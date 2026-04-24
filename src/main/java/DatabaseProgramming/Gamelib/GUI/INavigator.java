package DatabaseProgramming.Gamelib.GUI;


import java.sql.SQLException;

import DatabaseProgramming.Gamelib.Model.UserGame;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public interface INavigator {
	
	 void setRoot(BorderPane root);

	 void show(Node view);

	 void showMyGamesList();
	 
	 void showAllGamesList();

	 void showGameForm(Object gameData) throws SQLException;
}
