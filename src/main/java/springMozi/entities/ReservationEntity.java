package springMozi.entities;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ReservationEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(length=10000)
	@NotNull
	private long userId;
	@Column(length=10000)
	@NotNull
	private long showId;
	
	@Column(length=10000)
	@NotNull
	private String movieName;
	@Column(length=10000)
	@NotNull
	private String cinemaName;
	@Column(length=10000)
	@NotNull
	private int showRoom;
	@Column(length=10000)
	@NotNull
	private String showDimension;
	
	@Column(length=10000)
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm", timezone = "Europe/Budapest")
	@NotNull
	private Date showDate;
	
	@Column(length=10000)
	@NotNull
	private int price;
	
	@Column(length=10000)
	@NotNull
	private int seats[][];	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public int[][] getSeats() {
		return seats;
	}

	public void setSeats(int[][] seats) {
		this.seats = seats;
	}

	public String getShowDimension() {
		return showDimension;
	}

	public void setShowDimension(String showDimension) {
		this.showDimension = showDimension;
	}

	public int getShowRoom() {
		return showRoom;
	}

	public void setShowRoom(int showRoom) {
		this.showRoom = showRoom;
	}

	public long getShowId() {
		return showId;
	}

	public void setShowId(long showId) {
		this.showId = showId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
