package springMozi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class CinemaDateAndSeats implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(length=10000)
	@Lob
	@NotNull
	private String cinemaName;
	
	@Column(length=10000)
	@Lob
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm", timezone = "Europe/Budapest")
	@NotNull
	private Date showDate;
	
	@Column(length=10000)
	@NotNull
	private int showRoom;
	
	@Column(length=10000)
	@Lob
	@NotNull
	private String showDimension;
		
	@Column(length=10000)
	@Lob
	@JsonIgnore
	private int[][] seats = new int[5][10];
	
	@Column(length=10000)
	@NotNull
	private int ticketPrice;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "movie_Entity_id")
	private MovieEntity movieEntity;
		
	public CinemaDateAndSeats() {
	}
 	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public MovieEntity getMovieEntity() {
		return movieEntity;
	}
	
	public Date getShowDate() {
		return showDate;
		
	}
	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
		
	}
	
	@JsonIgnore
	public int[][] getSeats() {
		return seats;
	}
	
	@JsonIgnore	
	public void setSeats(int[][] seats,int ujErtek) {
		for(int i=0;i<seats.length;i++){
			this.seats[seats[i][0]][seats[i][1]] = ujErtek; 		
		}
	}

	public int getShowRoom() {
		return showRoom;
	}

	public void setShowRoom(int showRoom) {
		this.showRoom = showRoom;
	}

	public String getShowDimension() {
		return showDimension;
	}

	public void setShowDimension(String showDimension) {
		this.showDimension = showDimension;
	}
	
	
		
	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public void setSeatsToOne() {
		for(int i=0;i<5;i++){
 			for(int j=0;j<10;j++){
 				getSeats()[i][j] = 1;
 			}
 		}	
	}
	
	public int getSeatAt(int x,int y) {
		return getSeats()[x][y];
	}
	
	public ArrayList<String> checkIfSeatIsTaken(int[][] reservationSeats) {
		ArrayList<String> takenSeats = new ArrayList<String>();
		for(int i=0;i<reservationSeats.length;i++){
			if(this.seats[reservationSeats[i][0]][reservationSeats[i][1]] == 0) {
				takenSeats.add("Seat " + reservationSeats[i][0] + " " + reservationSeats[i][1] + " is taken.");
			}
		}
		return takenSeats;
	}
}
