package DatabaseProgramming.Gamelib.DAO;

import java.sql.SQLException;
import java.util.List;

import DatabaseProgramming.Gamelib.Model.User;
import DatabaseProgramming.Gamelib.Utility.Database;


public class UserDao implements IUserDao{

    private Database db;

    public UserDao(Database db) {
        this.db = db;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {

        List<User> users = db.getEntityManager()
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();

        return users;
    }

    @Override
    public User findByUsername(String username) throws SQLException {

        try {
            return db.getEntityManager().createQuery(
                    "SELECT u FROM User u WHERE u.username = :username",
                    User.class)
                    .setParameter("username", username)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User findByEmail(String email) throws SQLException {

        try {
            return db.getEntityManager().createQuery(
                    "SELECT u FROM User u WHERE u.email = :email",
                    User.class)
                    .setParameter("email", email)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void insertUser(User user) throws SQLException {

        db.getEntityManager().getTransaction().begin();
        db.getEntityManager().persist(user);
        db.getEntityManager().getTransaction().commit();

        System.out.println("User added!");
    }

    @Override
    public void updateUser(User user) throws SQLException {

        db.getEntityManager().getTransaction().begin();
        db.getEntityManager().merge(user);
        db.getEntityManager().getTransaction().commit();

        System.out.println("User updated!");
    }

    @Override
    public void removeUser(User user) throws SQLException {

        db.getEntityManager().getTransaction().begin();
        db.getEntityManager().remove(user);
        db.getEntityManager().getTransaction().commit();

        System.out.println("User deleted!");
    }
}