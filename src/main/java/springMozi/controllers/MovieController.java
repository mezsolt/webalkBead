package springMozi.controllers;

import java.util.ArrayList;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RestController;

import springMozi.entities.MovieEntity;
import springMozi.serviceImpls.MovieServiceImpl;
import springMozi.services.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {

	MovieService movieService;
	
	@Autowired
	public MovieController(MovieServiceImpl movieServiceImpl) {
		this.movieService = movieServiceImpl;
	}
	
	@GetMapping(path="",produces=MediaType.APPLICATION_JSON_VALUE)
	Iterable<MovieEntity> listMovies() {
		return movieService.listMovies();	
	}
	
	@PostMapping(path="",consumes=MediaType.APPLICATION_JSON_VALUE)
	void newMovie(@RequestBody MovieEntity newMovie) {
		movieService.newMovie(newMovie);
	}
	
	@DeleteMapping(path="/{id}")
	void deleteMovie(@PathVariable long id) {
		movieService.deleteMovie(id);
	}
	
	/*@PutMapping(path="",consumes=MediaType.APPLICATION_JSON_VALUE)
	void updateMovie(@PathVariable long id, @RequestBody MovieEntity updateMovie) {
		movieService.updateMovie(id, updateMovie);
	}*/
	
	/*@PostMapping(path="/{id}")
	void addMovieDate(@PathVariable long id,@RequestParam("newDate")@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") ArrayList<Date> newDate) {
		System.out.println(newDate.get(0).toString());
		movieService.addNewMovieDate(id,newDate);
	}*/
	
	@GetMapping(path="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	MovieEntity showMovie(@PathVariable long id) {
		return movieService.showOne(id);
		
	}
}
