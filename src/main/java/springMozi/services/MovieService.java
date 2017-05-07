package springMozi.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import springMozi.entities.ShowEntity;
import springMozi.entities.MovieEntity;
import springMozi.entities.ReservationEntity;

@Service
public interface MovieService {

	Iterable<MovieEntity> listMovies();
	void saveMovie(MovieEntity newMovie);
	void deleteMovie(long id);
	void updateMovie(long id,MovieEntity updateEntity);
	MovieEntity showOne(long id);
	List<MovieEntity> getMovieByGenres(ArrayList<String> genre);
	List<MovieEntity> getMovieByCinemas(ArrayList<String> cinema);
	boolean checkForMovieName(String movieName);
	boolean checkForShowId(long id);
	void deleteReservationsByMovieId(long movieId, ReservationService reservationService,MovieService movieService);
	
	void setSeats(long showId, int[][] seats, int newValue);
	int[][] showSeats(long showId);
	
	void deleteShow(long showId);
	void updateShow(long showId,ShowEntity updateShow);
	void newShow(long id,ShowEntity newShow);
	ShowEntity getShowById(long showId);
	
	//dao
	List<MovieEntity> findMovieAfterDate(Date date);
	
	//crudrepository
	List<MovieEntity> findByMovieName(String movieName);
	
	
	
}
