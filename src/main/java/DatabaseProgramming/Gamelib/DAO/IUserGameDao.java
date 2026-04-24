package DatabaseProgramming.Gamelib.DAO;

import java.sql.SQLException;
import java.util.List;

import DatabaseProgramming.Gamelib.Model.User;
import DatabaseProgramming.Gamelib.Model.UserGame;

public interface IUserGameDao {

    List<UserGame> getAllUserGames() throws SQLException;

    List<UserGame> getGamesByUser(User user) throws SQLException;
    
    UserGame getSpecificUserGame(int id) throws SQLException;

    void insertUserGame(UserGame userGame) throws SQLException;

    void updateUserGame(UserGame userGame) throws SQLException;

    void removeUserGame(UserGame userGame) throws SQLException;
}