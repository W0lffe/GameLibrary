package DatabaseProgramming.Gamelib.Utility;

import DatabaseProgramming.Gamelib.DAO.*;
import DatabaseProgramming.Gamelib.Service.DeveloperService;
import DatabaseProgramming.Gamelib.Service.GameService;
import DatabaseProgramming.Gamelib.Service.GenreService;

public class ServiceFactory {

	private final Database db;

	public ServiceFactory(Database db) {
		this.db = db;
	}

	public GameService createGameService() {

		IGameDao gameDao = new GameDao(db);
		IDeveloperDao developerDao = new DeveloperDao(db);
		IGenreDao genreDao = new GenreDao(db);
		IUserGameDao userGameDao = new UserGameDao(db);

		return new GameService(gameDao, developerDao, genreDao, userGameDao);
	}

	public DeveloperService createDeveloperService() {

		IDeveloperDao developerDao = new DeveloperDao(db);

		return new DeveloperService(developerDao);
	}

	public GenreService createGenreService() {

		IGenreDao genreDao = new GenreDao(db);

		return new GenreService(genreDao);
	}
}