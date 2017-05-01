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
import springMozi.entities.CinemaDateAndSeats;
import springMozi.entities.MovieEntity;
import springMozi.entities.UserEntity;
import springMozi.serviceImpls.MovieServiceImpl;
import springMozi.serviceImpls.UserServiceImpl;
import springMozi.services.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {

	MovieService movieService;
	MovieDAO movieDao;
	
	@Autowired
	public MovieController(MovieServiceImpl movieServiceImpl,MovieDAO movieDao) {
		this.movieService = movieServiceImpl;
		this.movieDao = movieDao;
	}
		
	@GetMapping(path="",produces=MediaType.APPLICATION_JSON_VALUE)
	Iterable<MovieEntity> listMovies() {
		return movieService.listMovies();	
	}
	
	@PostMapping(path="",consumes=MediaType.APPLICATION_JSON_VALUE)
	void newMovie(@RequestBody MovieEntity newMovie) {
		movieService.saveMovie(newMovie);
	}
	
	@PutMapping(path="/newshow/{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	void newShow(@PathVariable long id, @RequestBody CinemaDateAndSeats newShow) {
		movieService.newShow(id, newShow);
	}
	
	
	@DeleteMapping(path="/{id}")
	void deleteMovie(@PathVariable long id) {
		movieService.deleteMovie(id);
	}
	
	/*@PostMapping(path="/{id}")
	void addMovieDate(@PathVariable long id,@RequestParam("newDate")@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") ArrayList<Date> newDate) {
		System.out.println(newDate.get(0).toString());
		movieService.addNewMovieDate(id,newDate);
	}*/
	
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
	
	@GetMapping(path="/seats",produces=MediaType.APPLICATION_JSON_VALUE)
	int[][] getSeats(@RequestParam long showId) {		
		return movieService.getCinemaDateAndSeatsById(showId).getSeats();
	}
	
	/*@PutMapping(path="",consumes=MediaType.APPLICATION_JSON_VALUE)
	void updateMovie(@PathVariable long id, @RequestBody MovieEntity updateMovie) {
		movieService.updateMovie(id, updateMovie);
	}*/
	
	
	@GetMapping(path="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	MovieEntity showMovie(@PathVariable long id) {
		return movieService.showOne(id);
	}
	
	/*@GetMapping(path="/seats")
	@ResponseBody int showSeats(@RequestParam long id,@RequestParam int x,@RequestParam int y) {
		return movieService.showSeats(id, x, y);
		
	}*/
	//second commit
}
