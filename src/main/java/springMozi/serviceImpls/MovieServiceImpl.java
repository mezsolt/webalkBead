package springMozi.serviceImpls;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springMozi.entities.MovieEntity;
import springMozi.repositories.MovieRepository;
import springMozi.services.MovieService;

@Service
public class MovieServiceImpl implements MovieService{

	MovieRepository movieRepository;
	
	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public Iterable<MovieEntity> listMovies() {
		return movieRepository.findAll();
	}

	@Override
	public void newMovie(MovieEntity newMovie) {
		movieRepository.save(newMovie);
	}

	@Override
	public void deleteMovie(long id) {
		movieRepository.delete(id);
	}

	/*@Override
	public void updateMovie(long id, MovieEntity updateEntity) {
		movieRepository.findOne(id).setMovieName(updateEntity.getMovieName());
		movieRepository.findOne(id).setMovieDate(updateEntity.getMovieDate());
		movieRepository.save(movieRepository.findOne(id));
	}*/

	@Override
	public MovieEntity showOne(long id) {
		return movieRepository.findOne(id);
	}

	//public void addNewMovieDate(long id, ArrayList<Date> newDate) {
		/*for(Date e : newDate) {
			System.out.println(e.toString());
			movieRepository.findOne(id).getMovieDate().add(e);	
		}*/
		//movieRepository.findOne(id).getMovieDate().addAll(newDate);
		//movieRepository.save(movieRepository.findOne(id));		
	//}
}
