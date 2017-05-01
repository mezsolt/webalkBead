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
	void updateShow(long movieId,int showId,CinemaDateAndSeats updateShow);
	MovieEntity showOne(long id);
	void newShow(long id,CinemaDateAndSeats newShow);
	List<MovieEntity> getMovieByGenres(ArrayList<String> genre);
	CinemaDateAndSeats getCinemaDateAndSeatsById(long showId);
	void setSeats(long showId, int[][] seats, int newValue);
	int[][] showSeats(long showId);
	//int[][] getShowSeats(long movieId,int showId);
	
	//dao
	List<MovieEntity> findMovieAfterDate(Date date);
	
	//crudrepository
	List<MovieEntity> findByMovieName(String movieName);
	
	
	
}
