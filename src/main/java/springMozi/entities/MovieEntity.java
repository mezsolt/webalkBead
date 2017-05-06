package springMozi.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class MovieEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(length=10000,unique=true)
	@Lob
	@NotNull
	private String movieName;
	
	@Column(length=10000)
	@Lob
	@NotNull
	private ArrayList<String> availableCinemas;
	
	@Column(length=10000)
	@NotNull
	private int movieDuration;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Europe/Budapest")
	@NotNull
	private Date movieStartDate;
	
	@Column(length=10000)
	@Lob
	@NotNull
	private String movieDescription;
	
	@Column(length=10000)
	@Lob
	@NotNull
	private String movieDirector;
	
	@Column(length=10000)
	@Lob
	@NotNull
	private String movieCast;
	
	@Column(length=10000)
	@Lob
	@NotNull
	private ArrayList<String> movieDimension;
	
	@Column(length=10000)
	@NotNull
	private int movieAgeRestriction;
	
	@Column(length=10000)
	@Lob
	@NotNull
	private ArrayList<String> movieGenre;		
		
	@Column(length=10000)
	@Lob
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "MOVIE_ENTITY_ID")
	private List<CinemaDateAndSeats> dateAndSeats;
	
	
	public List<CinemaDateAndSeats> getDateAndSeats() {
		return dateAndSeats;
	}
		
	public ArrayList<String> getMovieDimension() {
		return movieDimension;
	}

	public void setMovieDimension(ArrayList<String> movieDimension) {
		this.movieDimension = movieDimension;
	}

	public String getMovieDirector() {
		return movieDirector;
	}

	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}

	public String getMovieCast() {
		return movieCast;
	}

	public void setMovieCast(String movieCast) {
		this.movieCast = movieCast;
	}

	public int getMovieAgeRestriction() {
		return movieAgeRestriction;
	}

	public void setMovieAgeRestriction(int movieAgeRestriction) {
		this.movieAgeRestriction = movieAgeRestriction;
	}

	public Date getMovieStartDate() {
		return movieStartDate;
	}

	public void setMovieStartDate(Date movieStartDate) {
		this.movieStartDate = movieStartDate;
	}

	public String getMovieDescription() {
		return movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

	public int getMovieDuration() {
		return movieDuration;
	}

	public void setMovieDuration(int movieDuration) {
		this.movieDuration = movieDuration;
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
	
	public ArrayList<String> getAvailableCinemas() {
		return availableCinemas;
	}
	public void setAvailableCinemas(ArrayList<String> availableCinemas) {
		this.availableCinemas = availableCinemas;
	}

	
}
