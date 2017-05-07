package springMozi.controllers;

import java.util.ArrayList;
import java.util.List;

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
	
	
	//listazas
	@GetMapping(path="",produces=MediaType.APPLICATION_JSON_VALUE)
	Iterable<ReservationEntity> listReservations() {
		return reservationService.listAllReservations();	
	}
	
	
	//uj reservation
	@PostMapping(path="",consumes=MediaType.APPLICATION_JSON_VALUE)
	void newReservation(@RequestBody ReservationEntity newReservation) {
		if(movieService.getShowById(newReservation.getShowId()).checkIfSeatIsTaken(newReservation.getSeats()).size()>0) {
			throw new seatsTakenException(movieService.getShowById(newReservation.getShowId()).checkIfSeatIsTaken(newReservation.getSeats()).toString());
		} 
		if(!userService.checkForId(newReservation.getUserId())) {
			throw new UserIdNotExistsException();
		}
		if(!movieService.checkForShowId(newReservation.getShowId())) {
			throw new ShowIdNotExistsException();
		}
		reservationService.newReservation(newReservation,movieService.getShowById(newReservation.getShowId()).getTicketPrice());
		movieService.getShowById(newReservation.getShowId()).setSeats(newReservation.getSeats(), 0);
		movieService.saveMovie(movieService.showOne(movieService.getShowById(newReservation.getShowId()).getMovieEntity().getId()));
		
	}
	
	
	//delete reservation
	@DeleteMapping(path="/{id}")
	void deleteReservation(@PathVariable long id) {
		movieService.getShowById(reservationService.findReservation(id).getShowId()).setSeats(reservationService.findReservation(id).getSeats(), 1);
		movieService.saveMovie(movieService.showOne(movieService.getShowById(reservationService.findReservation(id).getShowId()).getMovieEntity().getId()));
		reservationService.deleteReservation(id);
	}
	
	
	//update reservation
	@PutMapping(path="/{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	void updateReservation(@PathVariable long id, @RequestBody ReservationEntity updateReservation) {
		movieService.getShowById(reservationService.findReservation(id).getShowId()).setSeats(reservationService.findReservation(id).getSeats(), 1);
		movieService.saveMovie(movieService.showOne(movieService.getShowById(reservationService.findReservation(id).getShowId()).getMovieEntity().getId()));
		
		if(movieService.getShowById(updateReservation.getShowId()).checkIfSeatIsTaken(updateReservation.getSeats()).size()>0) {
			ArrayList<String> takenSeatsMessage = new ArrayList<>();
			takenSeatsMessage.addAll(movieService.getShowById(updateReservation.getShowId()).checkIfSeatIsTaken(updateReservation.getSeats()));
			
			movieService.getShowById(reservationService.findReservation(id).getShowId()).setSeats(reservationService.findReservation(id).getSeats(), 0);
			movieService.saveMovie(movieService.showOne(movieService.getShowById(reservationService.findReservation(id).getShowId()).getMovieEntity().getId()));
			
			throw new seatsTakenException(takenSeatsMessage.toString());
		} 
		if(!userService.checkForId(updateReservation.getUserId())) {
			throw new UserIdNotExistsException();
		}
		if(!movieService.checkForShowId(updateReservation.getShowId())) {
			throw new ShowIdNotExistsException();
		}
		movieService.getShowById(updateReservation.getShowId()).setSeats(updateReservation.getSeats(), 0);
		movieService.saveMovie(movieService.showOne(movieService.getShowById(updateReservation.getShowId()).getMovieEntity().getId()));
		reservationService.updateReservation(id, updateReservation,movieService.getShowById(updateReservation.getShowId()).getTicketPrice());
	}
	
	//reservation id alapjan
	@GetMapping(path="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	ReservationEntity showReservation(@PathVariable long id) {
		return reservationService.findReservation(id);
		
	}
	
	//reservation ulesei id alapjan
	@GetMapping(path="/seats/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	int[][] showReservationSeats(@PathVariable long id) {
		return reservationService.findReservation(id).getSeats();
		
	}
	
	//lekerdezesek
	@GetMapping(path="/userid/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	List<ReservationEntity> showReservationByUserId(@PathVariable long id) {
		return reservationService.findByUserId(id);
		
	}
	@GetMapping(path="/showid/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	List<ReservationEntity> showReservationByShowId(@PathVariable long id) {
		return reservationService.findByShowId(id);
		
	}
	@GetMapping(path="/cinema",produces=MediaType.APPLICATION_JSON_VALUE)
	List<ReservationEntity> showReservationByCinema(@RequestParam String cinemaName) {
		return reservationService.findByCinemaName(cinemaName);
		
	}
	@GetMapping(path="/moviename",produces=MediaType.APPLICATION_JSON_VALUE)
	List<ReservationEntity> showReservationByMovie(@RequestParam String movieName) {
		return reservationService.findByMovieName(movieName);
		
	}
}
