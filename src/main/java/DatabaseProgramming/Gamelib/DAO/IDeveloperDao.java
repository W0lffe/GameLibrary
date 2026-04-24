package DatabaseProgramming.Gamelib.DAO;

import java.sql.SQLException;
import java.util.List;
import DatabaseProgramming.Gamelib.Model.Developer;

public interface IDeveloperDao {

    List<Developer> getAllDevelopers() throws SQLException;

    Developer findSpecificDeveloperByName(String name) throws SQLException;

    void insertDeveloper(Developer developer) throws SQLException;

    void updateDeveloper(Developer developer) throws SQLException;

    void removeDeveloper(Developer developer) throws SQLException;
}