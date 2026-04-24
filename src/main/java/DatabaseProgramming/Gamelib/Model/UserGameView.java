package DatabaseProgramming.Gamelib.Model;

public class UserGameView {

	private int id;
	private String gameName;
	private String developer;
	private int released;
	private String genre;
	private int playtime;
	private int rating;

	public UserGameView(int id, String gameName, String developer, int released, String genre, int playtime,
			int rating) {
		super();
		this.id = id;
		this.gameName = gameName;
		this.developer = developer;
		this.released = released;
		this.genre = genre;
		this.playtime = playtime;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public int getReleased() {
		return released;
	}

	public void setReleased(int released) {
		this.released = released;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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

	@Override
	public String toString() {
		return "UserGameView [id=" + id + ", gameName=" + gameName + ", developer=" + developer + ", released="
				+ released + ", genre=" + genre + ", playtime=" + playtime + ", rating=" + rating + "]";
	}
	
	
	

}
