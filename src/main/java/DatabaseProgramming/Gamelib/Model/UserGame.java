package DatabaseProgramming.Gamelib.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserGame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int usergame_id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "game_id", nullable = false)
	private Game game;
	private int playtime;
	private int rating;
	
	public UserGame() {} 
	
	public UserGame(User user, Game game, int playtime, int rating) {
		super();
		this.user = user;
		this.game = game;
		this.playtime = playtime;
		this.rating = rating;
	}

	public int getUsergame_id() {
		return usergame_id;
	}

	public void setUsergame_id(int usergame_id) {
		this.usergame_id = usergame_id;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getPlaytime() {
		return playtime;
	}

	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
	
	
}
