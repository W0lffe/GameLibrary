package DatabaseProgramming.Gamelib.DAO;

import java.sql.SQLException;
import java.util.List;
import DatabaseProgramming.Gamelib.Model.Game;

public interface IGameDao {

    List<Game> getAllGames() throws SQLException;

    Game findSpecificGameByName(String title) throws SQLException;
    
    Game findSpecificGameById(int gameId) throws SQLException;

    void insertGame(Game game) throws SQLException;

    void updateGame(Game game) throws SQLException;

    void removeGame(Game game) throws SQLException;
}