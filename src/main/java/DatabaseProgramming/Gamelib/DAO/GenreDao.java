package DatabaseProgramming.Gamelib.DAO;

import java.sql.SQLException;
import java.util.List;

import DatabaseProgramming.Gamelib.Model.Genre;
import DatabaseProgramming.Gamelib.Utility.Database;


public class GenreDao implements IGenreDao {

    private Database db;

    public GenreDao(Database db) {
        this.db = db;
    }

    @Override
    public List<Genre> getAllGenres() throws SQLException {

        List<Genre> genres = db.getEntityManager()
                .createQuery("SELECT g FROM Genre g", Genre.class)
                .getResultList();

        return  genres;
    }
    
    @Override
    public Genre findGenreByName(String name) throws SQLException {

        try {
        	
            return db.getEntityManager().createQuery(
                    "SELECT g FROM Genre g WHERE g.name = :name",
                    Genre.class)
                    .setParameter("name", name)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public void insertGenre(Genre genre) throws SQLException {

        db.getEntityManager().getTransaction().begin();
        db.getEntityManager().persist(genre);
        db.getEntityManager().getTransaction().commit();

        System.out.println("Genre added!");
    }

    @Override
    public void updateGenre(Genre genre) throws SQLException {

        db.getEntityManager().getTransaction().begin();
        db.getEntityManager().merge(genre);
        db.getEntityManager().getTransaction().commit();

        System.out.println("Genre updated!");
    }

    @Override
    public void removeGenre(Genre genre) throws SQLException {

        db.getEntityManager().getTransaction().begin();
        db.getEntityManager().remove(genre);
        db.getEntityManager().getTransaction().commit();

        System.out.println("Genre deleted!");
    }
}