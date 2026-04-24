package DatabaseProgramming.Gamelib.DAO;

import java.sql.SQLException;
import java.util.List;

import DatabaseProgramming.Gamelib.Model.User;
import DatabaseProgramming.Gamelib.Model.UserGame;
import DatabaseProgramming.Gamelib.Utility.Database;

public class UserGameDao implements IUserGameDao {

    private Database db;

    public UserGameDao(Database db) {
        this.db = db;
    }

    @Override
    public List<UserGame> getAllUserGames() throws SQLException {

        List<UserGame> list = db.getEntityManager()
                .createQuery("SELECT ug FROM UserGame ug", UserGame.class)
                .getResultList();

        return list;
    }
    
    @Override
    public UserGame getSpecificUserGame(int id) throws SQLException {
    	
    	return db.getEntityManager()
    			.createQuery("SELECT ug FROM UserGame ug WHERE ug.usergame_id = :id",
    					UserGame.class)
    			.setParameter("id", id)
    			.getSingleResult();
    }

    @Override
    public List<UserGame> getGamesByUser(User user) throws SQLException {

        List<UserGame> list = db.getEntityManager()
                .createQuery(
                    "SELECT ug FROM UserGame ug WHERE ug.user = :user",
                    UserGame.class)
                .setParameter("user", user)
                .getResultList();
        
        return list;
    }

    @Override
    public void insertUserGame(UserGame userGame) throws SQLException {

        db.getEntityManager().getTransaction().begin();
        db.getEntityManager().persist(userGame);
        db.getEntityManager().getTransaction().commit();

        System.out.println("UserGame added!");
    }

    @Override
    public void updateUserGame(UserGame userGame) throws SQLException {

        db.getEntityManager().getTransaction().begin();
        db.getEntityManager().merge(userGame);
        db.getEntityManager().getTransaction().commit();

        System.out.println("UserGame updated!");
    }

    @Override
    public void removeUserGame(UserGame userGame) throws SQLException {

        db.getEntityManager().getTransaction().begin();
        db.getEntityManager().remove(userGame);
        db.getEntityManager().getTransaction().commit();

        System.out.println("UserGame deleted!");
    }
}