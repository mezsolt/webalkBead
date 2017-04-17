package springMozi.entities;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import springMozi.model.MoziDateAndSeats;

@Entity
public class MovieEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(length=10000)
	@Lob
	private String movieName;
	
	@Column(length=10000)
	@Lob
	private ArrayList<String>	availableCinemas;
	
	@Column(length=10000)
	@Lob
	private ArrayList<String>	movieGenre;
	
	/*@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm", timezone = "Europe/Budapest")
	private ArrayList<Date> movieDate;*/
	
	@Column(length=10000)
	@Lob
	private ArrayList<MoziDateAndSeats> datesAndSeats;
		
	public ArrayList<MoziDateAndSeats> getDatesAndSeats() {
		return datesAndSeats;
	}
	public void setDates(ArrayList<MoziDateAndSeats> datesAndSeats) {
		this.datesAndSeats = datesAndSeats;
	}		
	
	public ArrayList<String> getMovieGenre() {
		return movieGenre;
	}
	public void setMovieGenre(ArrayList<String> movieGenre) {
		this.movieGenre = movieGenre;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	/*public ArrayList<Date> getMovieDate() {
		return movieDate;
	}
	public void setMovieDate(ArrayList<Date> movieDate) {
		this.movieDate = movieDate;
	}*/
	
	public ArrayList<String> getAvailableCinemas() {
		return availableCinemas;
	}
	public void setAvailableCinemas(ArrayList<String> availableCinemas) {
		this.availableCinemas = availableCinemas;
	}

	/*public void addMovieDate(Date newDate) {
		this.movieDate.add(newDate);
	}*/
	
}
