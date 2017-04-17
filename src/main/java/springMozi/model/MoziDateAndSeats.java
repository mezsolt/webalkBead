package springMozi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class MoziDateAndSeats implements Serializable{

	private String cinemaName;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm", timezone = "Europe/Budapest")
	private Date showDate;
	
	@JsonIgnore
	private int[] seatsRow1 = {1,1,1,1,1,1,1,1,1,1};
	
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
	public int[] getSeatsRow1() {
		return seatsRow1;
	}
	
	@JsonIgnore
	public void setSeat(int seat,int seatTaken) {
		this.seatsRow1[seat] = seatTaken;
	}
	
}
