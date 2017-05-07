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
import springMozi.entities.ShowEntity;
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
	
	@Autowired
	public MovieController(MovieServiceImpl movieServiceImpl,ReservationServiceImpl reservationServiceImpl,MovieDAO movieDao) {
		this.movieService = movieServiceImpl;
		this.reservationService = reservationServiceImpl;	
		this.movieDao = movieDao;
	}
		
	//listazas
	@GetMapping(path="",produces=MediaType.APPLICATION_JSON_VALUE)
	Iterable<MovieEntity> listMovies() {
		return movieService.listMovies();	
	}
	
	
	//uj movie
	@PostMapping(path="",consumes=MediaType.APPLICATION_JSON_VALUE)
	void newMovie(@RequestBody MovieEntity newMovie) {
		if(movieService.checkForMovieName(newMovie.getMovieName())) {
			throw new MovieNameAlreadyExistsException();
		}
		movieService.saveMovie(newMovie);
	}
	
	//delete movie
	@DeleteMapping(path="/{id}")
	void deleteMovie(@PathVariable long id) {
		movieService.deleteReservationsByMovieId(id, reservationService,movieService);
		movieService.deleteMovie(id);
	}
	
	//update movie
	@PutMapping(path="/{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	void updateMovie(@PathVariable long id, @RequestBody MovieEntity updateMovie) {
		if(movieService.checkForMovieName(updateMovie.getMovieName())) {
			throw new MovieNameAlreadyExistsException();
		}
		movieService.deleteReservationsByMovieId(id, reservationService,movieService);
		movieService.updateMovie(id, updateMovie);
	}
	
	//movie by id
	@GetMapping(path="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	MovieEntity showMovie(@PathVariable long id) {
		return movieService.showOne(id);
	}
	
	//dao moviek egy release date utan
	@GetMapping(path="/movieafterrelease",produces=MediaType.APPLICATION_JSON_VALUE)
	Iterable<MovieEntity> listMoviesAfterDate(@RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd")Date date) {
		return movieDao.getMovieAfterDate(date);
	}
	
	//moviek movie nev alapjan
	@GetMapping(path="/moviename",produces=MediaType.APPLICATION_JSON_VALUE)
	Iterable<MovieEntity> findByMovieName(@RequestParam String movieName) {
		return movieService.findByMovieName(movieName);
	}
	
	//moviek genre alapjan	
	@GetMapping(path="/genre",produces=MediaType.APPLICATION_JSON_VALUE)
	Iterable<MovieEntity> findByMovieGenreIn(@RequestParam ArrayList<String> movieGenre) {
		return movieService.getMovieByGenres(movieGenre);
	}
	
	//moviek cinema alapjan
	@GetMapping(path="/cinema",produces=MediaType.APPLICATION_JSON_VALUE)
	Iterable<MovieEntity> findByMovieCinemaIn(@RequestParam ArrayList<String> movieCinemas) {
		return movieService.getMovieByCinemas(movieCinemas);
	}
	
	//show by id
	@GetMapping(path="/show/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	ShowEntity showById(@PathVariable long id) {
		return movieService.getShowById(id);
	}
	
	//uj show
	@PostMapping(path="/show/{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	void newShow(@PathVariable long id, @RequestBody ShowEntity newShow) {
		movieService.newShow(id, newShow);
	}	
	
	//update show
	@PutMapping(path="/show/{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	void updateShow(@PathVariable long id, @RequestBody ShowEntity updateShow) {
		reservationService.deleteReservationByShowId(id,movieService);
		movieService.updateShow(id, updateShow);
	}
	
	//delete show
	@DeleteMapping(path="/show/{id}")
	void deleteShow(@PathVariable long id) {
		reservationService.deleteReservationByShowId(id,movieService);
		movieService.deleteShow(id);
	}
	
		
	//show seats listazasa
	@GetMapping(path="/seatsall",produces=MediaType.APPLICATION_JSON_VALUE)
	int[][] getSeats(@RequestParam long showId) {		
		return movieService.getShowById(showId).getSeats();
	}
	
	//show seats specifikus at x y
	@GetMapping(path="/seatsspecific")
	@ResponseBody int showSeatsAt(@RequestParam long showId,@RequestParam int row,@RequestParam int column) {
		return movieService.getShowById(showId).getSeatAt(row, column);		
	}
}
