package DatabaseProgramming.Gamelib.GUI;

import java.sql.SQLException;
import DatabaseProgramming.Gamelib.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MainController {
	@FXML
	BorderPane root;
	@FXML
	private Label userLabel;
	
	private INavigator navigator;

    public void setNavigator(INavigator navigator) {
        this.navigator = navigator;
        this.navigator.setRoot(root);
    }
    
    @FXML
	public void initialize(){	
		userLabel.setText( "Logged in as" + "\n" + App.getUser().getUsername() );
	}
    
    @FXML
    private void handleMyGames() {
        navigator.showMyGamesList();
    }

    @FXML
    private void handleAddGame() throws SQLException {
        navigator.showGameForm(null);
    }
    
    @FXML
    private void handleAllGames() throws SQLException {
        navigator.showAllGamesList();;
    }
}