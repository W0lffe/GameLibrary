package DatabaseProgramming.Gamelib.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Developer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int developer_id;
	private String name;
	
	@OneToMany(mappedBy="developer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Game> games = new ArrayList<>();
	
	public Developer() {
		
	}
	
	public Developer(String name) {
		super();
		this.name = name;
	}
	public int getDeveloper_id() {
		return developer_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Game> getGames(){
		return games;
	}
	@Override
	public String toString() {
		return "Developer ID: " + developer_id + " | Name: " + name;
	}
	
	
	
}
