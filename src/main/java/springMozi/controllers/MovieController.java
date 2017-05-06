package springMozi.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springMozi.dao.MovieDAO;
import springMozi.dao.ReservationDAO;
import springMozi.entities.CinemaDateAndSeats;
import springMozi.entities.MovieEntity;
import springMozi.entities.ReservationEntity;
import springMozi.entities.UserEntity;
import springMozi.exceptions.MovieNameAlreadyExistsException;
import springMozi.exceptions.UsernameOrEmailAlreadyExistException;
import springMozi.serviceImpls.MovieServiceImpl;
import springMozi.serviceImpls.ReservationServiceImpl;
import springMozi.serviceImpls.UserServiceImpl;
import springMozi.services.MovieService;
import springMozi.services.ReservationService;

@RestController
@RequestMapping("/movie")
public class MovieController {

	private MovieService movieService;
	private ReservationService reservationService;
	
	private MovieDAO movieDao;
	private ReservationDAO reservationDAO;
	
	@Autowired
	public MovieController(MovieServiceImpl movieServiceImpl,ReservationServiceImpl reservationServiceImpl,MovieDAO movieDao,ReservationDAO reservationDAO) {
		this.movieService = movieServiceImpl;
		this.reservationService = reservationServiceImpl;	
		this.movieDao = movieDao;
		this.reservationDAO = reservationDAO;
	}
		
	@GetMapping(path="",produces=MediaType.APPLICATION_JSON_VALUE)
	Iterable<MovieEntity> listMovies() {
		return movieService.listMovies();	
	}
	
	@PostMapping(path="",consumes=MediaType.APPLICATION_JSON_VALUE)
	void newMovie(@RequestBody MovieEntity newMovie) {
		if(movieService.checkForMovieName(newMovie.getMovieName())) {
			throw new MovieNameAlreadyExistsException();
		}
		movieService.saveMovie(newMovie);
	}
	
	@DeleteMapping(path="/{id}")
	void deleteMovie(@PathVariable long id) {
		movieService.deleteReservationsByMovieId(id, reservationService);
		movieService.deleteMovie(id);
	}
	
	@PutMapping(path="",consumes=MediaType.APPLICATION_JSON_VALUE)
	void updateMovie(@PathVariable long id, @RequestBody MovieEntity updateMovie) {
		if(movieService.checkForMovieName(updateMovie.getMovieName())) {
			throw new MovieNameAlreadyExistsException();
		}
		movieService.updateMovie(id, updateMovie);
	}
	
	@GetMapping(path="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	MovieEntity showMovie(@PathVariable long id) {
		return movieService.showOne(id);
	}
	
	@GetMapping(path="/dao",produces=MediaType.APPLICATION_JSON_VALUE)
	Iterable<MovieEntity> listMoviesAfterDate(@RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd")Date date) {
		return movieDao.getMovieAfterDate(date);
	}
	
	@GetMapping(path="/rep",produces=MediaType.APPLICATION_JSON_VALUE)
	Iterable<MovieEntity> findByMovieName(@RequestParam String movieName) {
		return movieService.findByMovieName(movieName);
	}
		
	@GetMapping(path="/rep/genre",produces=MediaType.APPLICATION_JSON_VALUE)
	Iterable<MovieEntity> findByMovieGenreIn(@RequestParam ArrayList<String> movieGenre) {
		return movieService.getMovieByGenres(movieGenre);
	}
	
	@GetMapping(path="/rep/cinema",produces=MediaType.APPLICATION_JSON_VALUE)
	Iterable<MovieEntity> findByMovieCinemaIn(@RequestParam ArrayList<String> movieCinemas) {
		return movieService.getMovieByCinemas(movieCinemas);
	}
	
	//show
	@PutMapping(path="/newshow/{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	void newShow(@PathVariable long id, @RequestBody CinemaDateAndSeats newShow) {
		movieService.newShow(id, newShow);
	}	
	
	@PostMapping(path="/updateshow/{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	void updateShow(@PathVariable long showId, @RequestBody CinemaDateAndSeats updateShow) {
		reservationService.deleteReservationByShowId(updateShow.getId());
		movieService.updateShow(showId, updateShow);
	}
	
	@DeleteMapping(path="/show/{id}")
	void deleteShow(@PathVariable long id) {
		reservationService.deleteReservationByShowId(id);
		movieService.deleteShow(id);
	}
	
		
	//seats
	@GetMapping(path="/seatsall",produces=MediaType.APPLICATION_JSON_VALUE)
	int[][] getSeats(@RequestParam long showId) {		
		return movieService.getCinemaDateAndSeatsById(showId).getSeats();
	}
	

	@GetMapping(path="/seatsspecific")
	@ResponseBody int showSeatsAt(@RequestParam long showId,@RequestParam int x,@RequestParam int y) {
		return movieService.getCinemaDateAndSeatsById(showId).getSeatAt(x, y);		
	}
}
