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
	void saveMovie(MovieEntity newMovie);
	void deleteMovie(long id);
	void updateMovie(long id,MovieEntity updateEntity);
	MovieEntity showOne(long id);
	List<MovieEntity> getMovieByGenres(ArrayList<String> genre);
	List<MovieEntity> getMovieByCinemas(ArrayList<String> cinema);
	
	void setSeats(long showId, int[][] seats, int newValue);
	int[][] showSeats(long showId);
	
	void deleteShow(long showId);
	void updateShow(long movieId,int showId,CinemaDateAndSeats updateShow);
	void newShow(long id,CinemaDateAndSeats newShow);
	CinemaDateAndSeats getCinemaDateAndSeatsById(long showId);
	
	//dao
	List<MovieEntity> findMovieAfterDate(Date date);
	
	//crudrepository
	List<MovieEntity> findByMovieName(String movieName);
	
	
	
}
