package springMozi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springMozi.entities.MovieEntity;
import springMozi.entities.ReservationEntity;
import springMozi.serviceImpls.ReservationServiceImpl;
import springMozi.services.ReservationService;

@RestController("/reservation")
public class ReservationController {

	ReservationService reservationService;

	@Autowired
	public ReservationController(ReservationServiceImpl reservationServiceImpl) {
		super();
		this.reservationService = reservationServiceImpl;
	}
	
	@GetMapping(path="",produces=MediaType.APPLICATION_JSON_VALUE)
	Iterable<ReservationEntity> listReservations() {
		return reservationService.listAllReservations();	
	}
	
	@PostMapping(path="",consumes=MediaType.APPLICATION_JSON_VALUE)
	void newReservation(@RequestBody ReservationEntity newReservation) {
		reservationService.newReservation(newReservation);
	}
	
	@DeleteMapping(path="/{id}")
	void deleteReservation(@PathVariable long id) {
		reservationService.deleteReservation(id);
	}
	
	/*@PutMapping(path="",consumes=MediaType.APPLICATION_JSON_VALUE)
	void updateReservation(@PathVariable long id, @RequestBody ReservationEntity updateReservation) {
		movieService.updateReservation(id, updateReservation);
	}*/
	
	
	@GetMapping(path="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	ReservationEntity showReservation(@PathVariable long id) {
		return reservationService.findReservation(id);
		
	}
	
	
}
