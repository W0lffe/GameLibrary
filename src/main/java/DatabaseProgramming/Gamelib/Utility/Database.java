package DatabaseProgramming.Gamelib.Utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {

	private EntityManagerFactory emf;
	private EntityManager entityManager;
	
	public Database() {
		this.emf = Persistence.createEntityManagerFactory("JPA-PU");
    	this.entityManager = emf.createEntityManager();
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}