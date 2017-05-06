package springMozi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springMozi.entities.MovieEntity;
import springMozi.entities.ReservationEntity;
import springMozi.exceptions.BadUserNameException;
import springMozi.exceptions.ShowIdNotExistsException;
import springMozi.exceptions.UserIdNotExistsException;
import springMozi.exceptions.seatsTakenException;
import springMozi.serviceImpls.MovieServiceImpl;
import springMozi.serviceImpls.ReservationServiceImpl;
import springMozi.serviceImpls.UserServiceImpl;
import springMozi.services.MovieService;
import springMozi.services.ReservationService;
import springMozi.services.UserService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	private ReservationService reservationService;
	private MovieService movieService;
	private UserService userService;

	@Autowired
	public ReservationController(ReservationServiceImpl reservationServiceImpl,MovieServiceImpl movieServiceImpl,UserServiceImpl userServiceImpl) {
		super();
		this.reservationService = reservationServiceImpl;
		this.movieService = movieServiceImpl;
		this.userService = userServiceImpl;
	}
	
	@GetMapping(path="",produces=MediaType.APPLICATION_JSON_VALUE)
	Iterable<ReservationEntity> listReservations() {
		return reservationService.listAllReservations();	
	}
	
	@PostMapping(path="",consumes=MediaType.APPLICATION_JSON_VALUE)
	void newReservation(@RequestBody ReservationEntity newReservation) {
		if(movieService.getCinemaDateAndSeatsById(newReservation.getShowId()).checkIfSeatIsTaken(newReservation.getSeats()).size()>0) {
			throw new seatsTakenException(movieService.getCinemaDateAndSeatsById(newReservation.getShowId()).checkIfSeatIsTaken(newReservation.getSeats()).toString());
		} 
		if(!userService.checkForId(newReservation.getUserId())) {
			throw new UserIdNotExistsException();
		}
		if(!movieService.checkForShowId(newReservation.getShowId())) {
			throw new ShowIdNotExistsException();
		}
		reservationService.newReservation(newReservation,movieService.getCinemaDateAndSeatsById(newReservation.getShowId()).getTicketPrice());
		movieService.getCinemaDateAndSeatsById(newReservation.getShowId()).setSeats(newReservation.getSeats(), 0);
		movieService.saveMovie(movieService.showOne(movieService.getCinemaDateAndSeatsById(newReservation.getShowId()).getMovieEntity().getId()));
		
	}
	
	@GetMapping(path="/seats/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	int[][] showReservationSeats(@PathVariable long id) {
		return reservationService.findReservation(id).getSeats();
		
	}
	
	@DeleteMapping(path="/{id}")
	void deleteReservation(@PathVariable long id) {
		movieService.getCinemaDateAndSeatsById(reservationService.findReservation(id).getShowId()).setSeats(reservationService.findReservation(id).getSeats(), 1);
		movieService.saveMovie(movieService.showOne(movieService.getCinemaDateAndSeatsById(reservationService.findReservation(id).getShowId()).getMovieEntity().getId()));
		reservationService.deleteReservation(id);
	}
	
	@PutMapping(path="",consumes=MediaType.APPLICATION_JSON_VALUE)
	void updateReservation(@PathVariable long id, @RequestBody ReservationEntity updateReservation) {
		if(movieService.getCinemaDateAndSeatsById(updateReservation.getShowId()).checkIfSeatIsTaken(updateReservation.getSeats()).size()>0) {
			throw new seatsTakenException(movieService.getCinemaDateAndSeatsById(updateReservation.getShowId()).checkIfSeatIsTaken(updateReservation.getSeats()).toString());
		} 
		if(!userService.checkForId(updateReservation.getUserId())) {
			throw new UserIdNotExistsException();
		}
		if(!movieService.checkForShowId(updateReservation.getShowId())) {
			throw new ShowIdNotExistsException();
		}
		reservationService.updateReservation(id, updateReservation,movieService.getCinemaDateAndSeatsById(updateReservation.getShowId()).getTicketPrice());
	}
	
	
	@GetMapping(path="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	ReservationEntity showReservation(@PathVariable long id) {
		return reservationService.findReservation(id);
		
	}
	
	
}
