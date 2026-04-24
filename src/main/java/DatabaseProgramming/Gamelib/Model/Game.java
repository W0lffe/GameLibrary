package DatabaseProgramming.Gamelib.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int game_id;
	private String title;
	private int release_year;
		
	 @ManyToOne
	 @JoinColumn(name = "developer_id")
	 private Developer developer;
	 
	 @ManyToOne
	 @JoinColumn(name = "genre_id")
	 private Genre genre;
	 
	 public Game() {
		 
	 }
	 
	 public Game(String title, int year, Developer developer, Genre genre) {
		 super();
		 this.title = title;
		 this.release_year = year;
		 this.developer = developer;
		 this.genre = genre;
		 
	 }

	 public String getTitle() {
		 return title;
	 }

	 public void setTitle(String title) {
		 this.title = title;
	 }

	 public int getRelease_year() {
		 return release_year;
	 }

	 public void setRelease_year(int release_year) {
		 this.release_year = release_year;
	 }

	 public Developer getDeveloper() {
		 return developer;
	 }

	 public void setDeveloper(Developer developer) {
		 this.developer = developer;
	 }
	 
	 public int getGame_id() {
		 return game_id;
	 }
	 
	 public Genre getGenre() {
		return genre;
	}

	 public void setGenre(Genre genre) {
		 this.genre = genre;
	 }

	 @Override
	 public String toString() {
		return "Game ID: " + game_id + " | Title: " + title + " | Released: " + release_year + " | Developed by: " + developer;
	 }
	 
	 
	 
}
