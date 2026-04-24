package DatabaseProgramming.Gamelib.DAO;

import java.sql.SQLException;
import java.util.List;

import DatabaseProgramming.Gamelib.Model.Developer;
import DatabaseProgramming.Gamelib.Utility.Database;

public class DeveloperDao implements IDeveloperDao {
	
	private Database db;
	
	public DeveloperDao(Database db) {
		this.db = db;
	}
	
	@Override
	public List<Developer> getAllDevelopers() throws SQLException {
			
			List<Developer> developers = db.getEntityManager()
				    .createQuery("SELECT d FROM Developer d", Developer.class)
				    .getResultList();
			
			
			return developers;
	
	}
	
	@Override
	public Developer findSpecificDeveloperByName(String name) throws SQLException {
		
		try {
			
			return db.getEntityManager().createQuery(
		                "SELECT d FROM Developer d WHERE d.name = :name",
		                Developer.class)
			                .setParameter("name", name)
			                .getSingleResult();
			
		 } catch (Exception e) {
	            return null;
		 }
	}
	
	@Override
	public void insertDeveloper(Developer developer) throws SQLException {
		
		db.getEntityManager().getTransaction().begin();
		db.getEntityManager().persist(developer);
		db.getEntityManager().getTransaction().commit();

	    System.out.println("Developer to database! \n");
	}
	
	@Override
	public void updateDeveloper(Developer developer) throws SQLException {
				
		db.getEntityManager().getTransaction().begin();
		db.getEntityManager().merge(developer);
		db.getEntityManager().getTransaction().commit();
		
		System.out.println("Updated data! \n");
	}
	
	@Override
	public void removeDeveloper(Developer developer) throws SQLException {
		
		db.getEntityManager().getTransaction().begin();
		db.getEntityManager().remove(developer);
		db.getEntityManager().getTransaction().commit();
		
		System.out.println("Deleted data!");
	}
}
