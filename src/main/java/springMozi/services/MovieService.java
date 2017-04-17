package springMozi.services;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import springMozi.entities.MovieEntity;

@Service
public interface MovieService {

	Iterable<MovieEntity> listMovies();
	void newMovie(MovieEntity newMovie);
	void deleteMovie(long id);
	//void updateMovie(long id,MovieEntity updateEntity);
	MovieEntity showOne(long id);
	//void addNewMovieDate(long id,ArrayList<Date> newDate);
}
