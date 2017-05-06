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
import springMozi.serviceImpls.MovieServiceImpl;
import springMozi.serviceImpls.ReservationServiceImpl;
import springMozi.services.MovieService;
import springMozi.services.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	private ReservationService reservationService;
	private MovieService movieService;

	@Autowired
	public ReservationController(ReservationServiceImpl reservationServiceImpl,MovieServiceImpl movieServiceImpl) {
		super();
		this.reservationService = reservationServiceImpl;
		this.movieService = movieServiceImpl;
	}
	
	@GetMapping(path="",produces=MediaType.APPLICATION_JSON_VALUE)
	Iterable<ReservationEntity> listReservations() {
		return reservationService.listAllReservations();	
	}
	
	@PostMapping(path="",consumes=MediaType.APPLICATION_JSON_VALUE)
	void newReservation(@RequestBody ReservationEntity newReservation,@RequestParam long showId) {
		reservationService.newReservation(newReservation);
		movieService.getCinemaDateAndSeatsById(showId).setSeats(newReservation.getSeats(), 0);
		movieService.saveMovie(movieService.showOne(movieService.getCinemaDateAndSeatsById(showId).getMovieEntity().getId()));
	}
	
	@GetMapping(path="/seats/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	int[][] showReservationSeats(@PathVariable long id) {
		return reservationService.findReservation(id).getSeats();
		
	}
	
	@DeleteMapping(path="/{id}")
	void deleteReservation(@PathVariable long id) {
		reservationService.deleteReservation(id);
	}
	
	@PutMapping(path="",consumes=MediaType.APPLICATION_JSON_VALUE)
	void updateReservation(@PathVariable long id, @RequestBody ReservationEntity updateReservation) {
		reservationService.updateReservation(id, updateReservation);
	}
	
	
	@GetMapping(path="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	ReservationEntity showReservation(@PathVariable long id) {
		return reservationService.findReservation(id);
		
	}
	
	
}
