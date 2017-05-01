package springMozi.serviceImpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springMozi.dao.MovieDAO;
import springMozi.entities.CinemaDateAndSeats;
import springMozi.entities.MovieEntity;
import springMozi.repositories.MovieRepository;
import springMozi.services.MovieService;

@Service
public class MovieServiceImpl implements MovieService{

	MovieRepository movieRepository;
	MovieDAO movieDao;
	
	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository,MovieDAO movieDao) {
		this.movieRepository = movieRepository;
		this.movieDao = movieDao;
	}

	@Override
	public Iterable<MovieEntity> listMovies() {
		return movieRepository.findAll();
	}

	@Override
	public void saveMovie(MovieEntity newMovie) {
		movieRepository.save(newMovie);
	}

	@Override
	public void deleteMovie(long id) {
		movieRepository.delete(id);
	}

	@Override
	public MovieEntity showOne(long id) {
		return movieRepository.findOne(id);
	}

	@Override
	public void newShow(long id,CinemaDateAndSeats newShow) {
		for(int i=0;i<5;i++){
 			for(int j=0;j<10;j++){
 				newShow.getSeats()[i][j] = 1;
 			}
 		}	
		movieRepository.findOne(id).getDateAndSeats().add(newShow);
		movieRepository.save(movieRepository.findOne(id));	
	}

	@Override
	public void updateMovie(long id, MovieEntity updateEntity) {
		movieRepository.findOne(id).setMovieName(updateEntity.getMovieName());
		movieRepository.findOne(id).setAvailableCinemas(updateEntity.getAvailableCinemas());
		movieRepository.findOne(id).setMovieDuration(updateEntity.getMovieDuration());
		movieRepository.findOne(id).setMovieStartDate(updateEntity.getMovieStartDate());
		movieRepository.findOne(id).setMovieDescription(updateEntity.getMovieDescription());
		movieRepository.findOne(id).setMovieDirector(updateEntity.getMovieDirector());
		movieRepository.findOne(id).setMovieCast(updateEntity.getMovieCast());
		movieRepository.findOne(id).setMovieDimension(updateEntity.getMovieDimension());
		movieRepository.findOne(id).setMovieAgeRestriction(updateEntity.getMovieAgeRestriction());
		movieRepository.findOne(id).setMovieGenre(updateEntity.getMovieGenre());
		
		movieRepository.save(movieRepository.findOne(id));
	}

	@Override
	public int[][] showSeats(long showId) {
		/*int[][] returnTomb = null;
		for(int i=0;i<movieRepository.findOne(movieId).getDateAndSeats().size();i++) {
			if(movieRepository.findOne(movieId).getDateAndSeats().get(i).getId()==showId){
				returnTomb = movieRepository.findOne(movieId).getDateAndSeats().get(i).getSeats();
			}
		}
		return returnTomb;*/
		return getCinemaDateAndSeatsById(showId).getSeats();
	}
	
	@Override
	public void updateShow(long movieId, int showId, CinemaDateAndSeats updateShow) {
		movieRepository.findOne(movieId).getDateAndSeats().get(showId).setCinemaName(updateShow.getCinemaName());
		movieRepository.findOne(movieId).getDateAndSeats().get(showId).setShowDate(updateShow.getShowDate());
		movieRepository.findOne(movieId).getDateAndSeats().get(showId).setShowRoom(updateShow.getShowRoom());
		movieRepository.findOne(movieId).getDateAndSeats().get(showId).setShowDimension(updateShow.getShowDimension());
		
		movieRepository.save(movieRepository.findOne(movieId));
	}

	@Override
	public void setSeats(long showId,int[][] seats, int newValue) {
		getCinemaDateAndSeatsById(showId).setSeats(seats,newValue);
	}
		
	public List<MovieEntity> findMovieAfterDate(Date date) {
		return movieDao.getMovieAfterDate(date);
	}

	@Override
	public List<MovieEntity> findByMovieName(String movieName) {
		return movieRepository.findByMovieName(movieName);
	}

	
	@Override
	public List<MovieEntity> getMovieByGenres(ArrayList<String> genre) {
		List<MovieEntity> movieList =  movieDao.getAllMovie();
		List<MovieEntity> moviesByGenre = new ArrayList<MovieEntity>();

		for(int i=0;i<movieList.size();i++) {
			if(movieList.get(i).getMovieGenre().containsAll(genre)){
				moviesByGenre.add(movieList.get(i));
			}		
		}

		return moviesByGenre;
	}

	@Override
	public CinemaDateAndSeats getCinemaDateAndSeatsById(long showId) {
		CinemaDateAndSeats dateAndSeats = null;
		/*for(int i=0;i<movieRepository.findOne(movieId).getDateAndSeats().size();i++) {
			if(movieRepository.findOne(movieId).getDateAndSeats().get(i).getId()==showId){
				dateAndSeats = movieRepository.findOne(movieId).getDateAndSeats().get(i);
			}
		}*/
		for(MovieEntity me : movieRepository.findAll()) {
			for(int i=0;i<me.getDateAndSeats().size();i++) {
				if(me.getDateAndSeats().get(i).getId()==showId){
					dateAndSeats = me.getDateAndSeats().get(i);
				}
			}
		}
		return dateAndSeats;
	}


	/*@Override
	public int[][] getShowSeats(long movieId,int showId) {
		return movieRepository.findOne(movieId).getDateAndSeats().get(showId).getSeats();
	}*/

}
