package DatabaseProgramming.Gamelib.DAO;

import java.sql.SQLException;
import java.util.List;
import DatabaseProgramming.Gamelib.Model.Genre;

public interface IGenreDao {

    List<Genre> getAllGenres() throws SQLException;

    Genre findGenreByName(String name) throws SQLException;

    void insertGenre(Genre genre) throws SQLException;

    void updateGenre(Genre genre) throws SQLException;

    void removeGenre(Genre genre) throws SQLException;
}