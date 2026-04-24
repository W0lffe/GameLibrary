package DatabaseProgramming.Gamelib.Model;

public class GameView {
	
	private String title;
	private int id;
	private String release_year;
	private String developer;
	private String genre;
	
	public GameView(String title, int id, String release_year, String developer, String genre) {
		super();
		this.title = title;
		this.id = id;
		this.release_year = release_year;
		this.developer = developer;
		this.genre = genre;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRelease_year() {
		return release_year;
	}
	public void setRelease_year(String release_year) {
		this.release_year = release_year;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
}
