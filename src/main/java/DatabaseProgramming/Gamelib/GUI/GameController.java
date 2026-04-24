package DatabaseProgramming.Gamelib.GUI;


import java.sql.SQLException;
import java.util.List;
import DatabaseProgramming.Gamelib.App;
import DatabaseProgramming.Gamelib.Model.Developer;
import DatabaseProgramming.Gamelib.Model.Game;
import DatabaseProgramming.Gamelib.Model.Genre;
import DatabaseProgramming.Gamelib.Model.UserGame;
import DatabaseProgramming.Gamelib.Service.DeveloperService;
import DatabaseProgramming.Gamelib.Service.GameService;
import DatabaseProgramming.Gamelib.Service.GenreService;
import DatabaseProgramming.Gamelib.Utility.Validation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class GameController {

	@FXML
	private ComboBox<String> developerCombo, genreCombo, ratingCombo;

	@FXML
	private VBox genreContainer, developerContainer;

	@FXML
	private Label infoLabel, titleLabel;
	@FXML
	private Button submitButton;
	
	@FXML
	private TextField gameNameField, releaseYearField, playtimeField;

	private TextField devInput, genreInput;
	
	private UserGame userGameToEdit;
	

	public void initializeView(Object gameData) throws SQLException {
		DeveloperService devServ = App.getDevService();
		List<Developer> developers = devServ.getAllDevelopers();

		GenreService genreService = App.getGenreService();
		List<Genre> genres = genreService.getAllGenres();

		if (developers != null) {
			for (Developer dev : developers) {
				developerCombo.getItems().add(dev.getName());
			}
		}

		if (genres != null) {
			for (Genre genre : genres) {
				genreCombo.getItems().add(genre.getName());
			}
		}

		ratingCombo.getItems().addAll("1", "2", "3", "4", "5");
		developerCombo.getItems().add("Other");
		genreCombo.getItems().add("Other");

		developerCombo.getSelectionModel().selectFirst();
		genreCombo.getSelectionModel().selectFirst();
		ratingCombo.getSelectionModel().selectFirst();
		
		if(gameData != null && gameData instanceof UserGame) {
			UserGame userGame = (UserGame) gameData;
			setFieldValuesFromUserGame(userGame);
			userGameToEdit = userGame;
		}
		
		if(gameData != null && gameData instanceof Game) {
			Game game = (Game) gameData;
			setFieldValuesFromGame(game);
		}
	}

	@FXML
	private void handleSubmit() throws SQLException, InterruptedException {

		if (!infoLabel.getText().isEmpty()) {
			infoLabel.setText("");
		}

		String gameName = gameNameField.getText().toString();
		String playtime = playtimeField.getText().toString();
		String releaseYear = releaseYearField.getText().toString();
		String rating = ratingCombo.getSelectionModel().getSelectedItem().toString();
		String developer = developerCombo.getSelectionModel().getSelectedItem().toString();
		String genre = genreCombo.getSelectionModel().getSelectedItem().toString();

		if (developer.equals("Other")) {
			developer = devInput.getText().toString();
		}

		if (genre.equals("Other")) {
			genre = genreInput.getText().toString();
		}

		String[] inputStrings = { gameName, developer, genre };
		String[] inputNumbers = { releaseYear, playtime, rating };

		if (Validation.validateStrings(inputStrings) && Validation.validateNumbers(inputNumbers)) {

			GameService gameService = App.getGameService();
			
			boolean submitSuccess;
			
			if(userGameToEdit == null) {
				submitSuccess = gameService.addGameForUser(gameName, developer, genre, Integer.parseInt(playtime),
						Integer.parseInt(rating), Integer.parseInt(releaseYear), App.getUser());
			} else {
		
				submitSuccess = gameService.updateGameForUser(gameName, developer, genre, Integer.parseInt(playtime),
						Integer.parseInt(rating), Integer.parseInt(releaseYear), userGameToEdit);
			}

			if (submitSuccess) {
				infoLabel.setText(
						userGameToEdit == null ? "Game added successfully!" : "Game updated successfully!"
				);
				resetForm();
			} else {
				infoLabel.setText(
						userGameToEdit == null ? "Error while adding game to database!" : "Error updating the game!"
				);
			}

		} else {
			infoLabel.setText("Please fill out text fields and check format!");
			return;
		}

	}

	private void resetForm() {
		gameNameField.setText("");
		releaseYearField.setText("");
		playtimeField.setText("");
		developerCombo.getSelectionModel().selectFirst();
		genreCombo.getSelectionModel().selectFirst();
		ratingCombo.getSelectionModel().selectFirst();
	}
	
	private void setFieldValuesFromUserGame(UserGame userGame) {
		gameNameField.setText( 
				userGame.getGame().getTitle() 
		);
		releaseYearField.setText( 
				String.valueOf( userGame.getGame().getRelease_year() )
		);
		playtimeField.setText(
				String.valueOf( userGame.getPlaytime() )
		);
		developerCombo.getSelectionModel().select(
				userGame.getGame().getDeveloper().getName()
		);
		genreCombo.getSelectionModel().select(
				userGame.getGame().getGenre().getName()
		);
		ratingCombo.getSelectionModel().select(
				String.valueOf( userGame.getRating() )
		);
		submitButton.setText("Submit");
		titleLabel.setText("🎮 Edit Game");
	}
	
	private void setFieldValuesFromGame(Game game) {
		gameNameField.setText( 
				game.getTitle() 
		);
		releaseYearField.setText( 
				String.valueOf( game.getRelease_year() )
		);
		developerCombo.getSelectionModel().select(
				game.getDeveloper().getName()
		);
		genreCombo.getSelectionModel().select(
				game.getGenre().getName()
		);
		
	}

	@FXML
	private void handleDeveloperChange() {
		String currentChoice = developerCombo.getValue().toString();
		if (currentChoice.equals("Other")) {

			devInput = new TextField();
			devInput.setPromptText("Enter developer name");
			devInput.setMaxWidth(250);

			developerContainer.getChildren().add(devInput);
		} else {
			developerContainer.getChildren().clear();
		}

	}

	@FXML
	private void handleGenreChange() {
		String currentChoice = genreCombo.getValue().toString();
		if (currentChoice.equals("Other")) {

			genreInput = new TextField();
			genreInput.setPromptText("Enter genre");
			genreInput.setMaxWidth(250);

			genreContainer.getChildren().add(genreInput);

		} else {
			genreContainer.getChildren().clear();
		}
	}

}
