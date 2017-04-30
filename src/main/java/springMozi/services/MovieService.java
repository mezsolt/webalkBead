package springMozi.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import springMozi.entities.CinemaDateAndSeats;
import springMozi.entities.MovieEntity;

@Service
public interface MovieService {

	Iterable<MovieEntity> listMovies();
	void newMovie(MovieEntity newMovie);
	void deleteMovie(long id);
	void updateMovie(long id,MovieEntity updateEntity);
	void updateShow(long movieId,int showId,CinemaDateAndSeats updateShow);
	MovieEntity showOne(long id);
	int[][] showSeats(long movieId,int showId);
	void newShow(long id,CinemaDateAndSeats newShow);
	void setSeats(long movieId,int showId,int[][] seats,int newValue);
	List<MovieEntity> getMovieByGenres(ArrayList<String> genre);
	
	//dao
	List<MovieEntity> findMovieAfterDate(Date date);
	
	//crudrepository
	List<MovieEntity> findByMovieName(String movieName);
	
	
	
	
}
