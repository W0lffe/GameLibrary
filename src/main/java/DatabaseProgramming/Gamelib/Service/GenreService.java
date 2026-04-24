package DatabaseProgramming.Gamelib.Service;

import java.sql.SQLException;
import java.util.List;

import DatabaseProgramming.Gamelib.DAO.IGenreDao;
import DatabaseProgramming.Gamelib.Model.Genre;

public class GenreService {

	private IGenreDao genreDao;
	
	public GenreService(IGenreDao genreDao) {
		this.genreDao = genreDao;
	}
	
	public List<Genre> getAllGenres() throws SQLException{
		
		try {
			
			return genreDao.getAllGenres();		
			
		} catch(Exception e) {
			
			return null;
		}
	
	}
	
}
