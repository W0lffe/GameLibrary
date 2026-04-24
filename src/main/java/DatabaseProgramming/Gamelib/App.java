package DatabaseProgramming.Gamelib;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import DatabaseProgramming.Gamelib.DAO.UserDao;
import DatabaseProgramming.Gamelib.GUI.INavigator;
import DatabaseProgramming.Gamelib.GUI.MainController;
import DatabaseProgramming.Gamelib.GUI.Navigator;
import DatabaseProgramming.Gamelib.Utility.Database;
import DatabaseProgramming.Gamelib.Utility.ServiceFactory;
import DatabaseProgramming.Gamelib.Model.User;
import DatabaseProgramming.Gamelib.Service.DeveloperService;
import DatabaseProgramming.Gamelib.Service.GameService;
import DatabaseProgramming.Gamelib.Service.GenreService;

public class App extends Application
{
		
		private static User user;
		private static GameService gameService;
		private static DeveloperService devService;
		private static GenreService genreService;
		private static INavigator navigator;
		
		@Override
	    public void start(Stage stage) throws Exception {
			
			Database db = new Database();
			ServiceFactory serviceFactory = new ServiceFactory(db);
			gameService = serviceFactory.createGameService();
			devService = serviceFactory.createDeveloperService();
			genreService = serviceFactory.createGenreService();
					
			UserDao ud = new UserDao(new Database());
			user = ud.findByUsername("LocalUser");
			
			FXMLLoader loader = new FXMLLoader(
					 getClass().getResource("/layout/MainView.fxml")
			);
			
			BorderPane root = loader.load();
			/*
	        BorderPane root = FXMLLoader.load(
	                getClass().getResource("/layout/MainView.fxml")
	        );
	        
	        */
	        
			MainController controller = loader.getController();
	    	navigator = new Navigator();
	    	controller.setNavigator(navigator);
	   
	        Scene scene = new Scene(root, 1280, 720);
	        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

	        stage.setTitle("Game Library");
	        stage.setScene(scene);
	        stage.show();
	    }
		
    public static void main( String[] args ) throws ClassNotFoundException, SQLException, InterruptedException
    {
    	launch();
    }
    
	public static User getUser() {
		return user;
	}

	public static GameService getGameService() {
		return gameService;
	}

	public static DeveloperService getDevService() {
		return devService;
	}

	public static GenreService getGenreService() {
		return genreService;
	}

	public static INavigator getNavigator() {
		return navigator;
	}	
	
	
   
}
