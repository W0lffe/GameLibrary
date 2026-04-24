package DatabaseProgramming.Gamelib.Service;

import java.sql.SQLException;
import java.util.List;
import DatabaseProgramming.Gamelib.DAO.IDeveloperDao;
import DatabaseProgramming.Gamelib.Model.Developer;

public class DeveloperService {
	
	private IDeveloperDao developerDao;
	
	public DeveloperService(IDeveloperDao developerDao) {
		this.developerDao = developerDao;
	}
	
	public List<Developer> getAllDevelopers() throws SQLException{
			
		try {
			
			return developerDao.getAllDevelopers();		
			
		} catch(Exception e) {
			
			return null;
		}
	
	}
}
