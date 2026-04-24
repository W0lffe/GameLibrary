package DatabaseProgramming.Gamelib.DAO;

import java.sql.SQLException;
import java.util.List;

import DatabaseProgramming.Gamelib.Model.Game;
import DatabaseProgramming.Gamelib.Utility.Database;

public class GameDao implements IGameDao {
	
	private Database db;
	
	public GameDao(Database db) {
		this.db = db;
	}
	
	@Override
	public List<Game> getAllGames() throws SQLException {
				
		List<Game> games = db.getEntityManager()
			    .createQuery("SELECT g FROM Game g", Game.class)
			    .getResultList();
		
		
		return games;

	}
		
	@Override
	public Game findSpecificGameByName(String title) throws SQLException {
	
		try {
			return db.getEntityManager().createQuery(
		                "SELECT g FROM Game g WHERE g.title = :title",
		                Game.class)
			                .setParameter("title", title)
			                .getSingleResult();
			
			} catch (Exception e) {
				return null;
			}
	    }
	
	public Game findSpecificGameById(int gameId) throws SQLException {
	
		try {
			return db.getEntityManager().createQuery(
		                "SELECT g FROM Game g WHERE g.game_id = :gameId",
		                Game.class)
			                .setParameter("gameId", gameId)
			                .getSingleResult();
			
			} catch (Exception e) {
				return null;
			}
	    }
	
	@Override
	public void insertGame(Game game) throws SQLException {
			
		db.getEntityManager().getTransaction().begin();
		db.getEntityManager().persist(game);
		db.getEntityManager().getTransaction().commit();

	    System.out.println("Added to database! \n");
	}
	
	@Override
	public void updateGame(Game game) throws SQLException {
				
		db.getEntityManager().getTransaction().begin();
		db.getEntityManager().merge(game);
		db.getEntityManager().getTransaction().commit();
		
		System.out.println("Updated data! \n");
	}
	
	@Override
	public void removeGame(Game game) throws SQLException {
		
		db.getEntityManager().getTransaction().begin();
		db.getEntityManager().remove(game);
		db.getEntityManager().getTransaction().commit();
		
		System.out.println("Deleted data!");
	}
}
