package DatabaseProgramming.Gamelib.Service;

import java.sql.SQLException;
import java.util.List;
import DatabaseProgramming.Gamelib.DAO.IDeveloperDao;
import DatabaseProgramming.Gamelib.DAO.IGameDao;
import DatabaseProgramming.Gamelib.DAO.IGenreDao;
import DatabaseProgramming.Gamelib.DAO.IUserGameDao;
import DatabaseProgramming.Gamelib.Model.Developer;
import DatabaseProgramming.Gamelib.Model.Game;
import DatabaseProgramming.Gamelib.Model.GameView;
import DatabaseProgramming.Gamelib.Model.Genre;
import DatabaseProgramming.Gamelib.Model.User;
import DatabaseProgramming.Gamelib.Model.UserGame;
import DatabaseProgramming.Gamelib.Model.UserGameView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameService {

	private IGameDao gameDao;
	private IDeveloperDao developerDao;
	private IGenreDao genreDao;
	private IUserGameDao userGameDao;

	public GameService(IGameDao gameDao, IDeveloperDao developerDao, IGenreDao genreDao, IUserGameDao userGameDao) {
		this.gameDao = gameDao;
		this.developerDao = developerDao;
		this.genreDao = genreDao;
		this.userGameDao = userGameDao;
	}

	public ObservableList<GameView> getAllGames() throws SQLException {
		
		ObservableList<GameView> list = FXCollections.observableArrayList();
		try {
			List<Game> allGames = gameDao.getAllGames();
			for(Game game : allGames) {
				list.add( new GameView(
						game.getTitle(),
						game.getGame_id(),
						String.valueOf(game.getRelease_year()),
						game.getDeveloper().getName(),
						game.getGenre().getName()
				));
			}
			
			return list;
			
		} catch (Exception e) {
			return list;
		}
		
	}
	
	public ObservableList<UserGameView> getAllGamesByUser(User user) throws SQLException {
		
		ObservableList<UserGameView> list = FXCollections.observableArrayList();
		try {
			
			List<UserGame> userGames = userGameDao.getGamesByUser(user);
	
			for (UserGame ug : userGames) {
			    list.add(new UserGameView(
			        ug.getUsergame_id(),
			        ug.getGame().getTitle(),
			        ug.getGame().getDeveloper().getName(),
			        ug.getGame().getRelease_year(),
			        ug.getGame().getGenre().getName(),
			        ug.getPlaytime(),
			        ug.getRating()
			    ));
			}
			
			return list;
			
		} catch (Exception e) {
			return list;
		}

	}
	
	public UserGame getSpecificUserGame(int userGameId) throws SQLException {
		return userGameDao.getSpecificUserGame(userGameId);
	}
	
	public Game getSpecificGameById(int gameId) throws SQLException {
		return gameDao.findSpecificGameById(gameId);
	}
	
	public boolean updateGameForUser(String gameName, String developerName, String genreName, int playtime, int rating,
			int releaseYear, UserGame existingUg) throws SQLException {
			
		try {
			Developer developer = developerDao.findSpecificDeveloperByName(developerName);
			if (developer == null) {
				developer = new Developer(developerName);
				developerDao.insertDeveloper(developer);
			}

			Genre genre = genreDao.findGenreByName(genreName);
			if (genre == null) {
				genre = new Genre(genreName);
				genreDao.insertGenre(genre);
			}
			
			Game game = gameDao.findSpecificGameByName(gameName);

		    if (game == null) {
		        game = new Game(gameName, releaseYear, developer, genre);
		        gameDao.insertGame(game);
		    }
			
		    existingUg.setGame(game);
		    existingUg.setPlaytime(playtime);
		    existingUg.setRating(rating);
		    userGameDao.updateUserGame(existingUg);
		    
		    return true;
		    
		} catch (Exception e) {
			return false;
		}
		
	}

	public void removeUserGame(UserGame userGame) throws SQLException {
		
		try {
	
			userGameDao.removeUserGame(userGame);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean addGameForUser(String gameName, String developerName, String genreName, int playtime, int rating,
			int releaseYear, User user) throws SQLException {

		try {
			Developer developer = developerDao.findSpecificDeveloperByName(developerName);
			if (developer == null) {
				developer = new Developer(developerName);
				developerDao.insertDeveloper(developer);
			}

			Genre genre = genreDao.findGenreByName(genreName);
			if (genre == null) {
				genre = new Genre(genreName);
				genreDao.insertGenre(genre);
			}
			
			Game game = gameDao.findSpecificGameByName(gameName);

		    if (game == null) {
		        game = new Game(gameName, releaseYear, developer, genre);
		        gameDao.insertGame(game);
		    }
			
		    UserGame userGame = new UserGame(user, game, playtime, rating);
		    userGameDao.insertUserGame(userGame);
		    
		    return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}
}
