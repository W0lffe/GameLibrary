package DatabaseProgramming.Gamelib.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Genre {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int genre_id;
	
	private String name;
	
	public Genre() {
		
	}
	
	public Genre(String name) {
		super();
		this.name = name;
	}

	public int getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
