package DatabaseProgramming.Gamelib.DAO;

import java.sql.SQLException;
import java.util.List;
import DatabaseProgramming.Gamelib.Model.User;

public interface IUserDao {

    List<User> getAllUsers() throws SQLException;

    User findByUsername(String username) throws SQLException;

    User findByEmail(String email) throws SQLException;

    void insertUser(User user) throws SQLException;

    void updateUser(User user) throws SQLException;

    void removeUser(User user) throws SQLException;
}