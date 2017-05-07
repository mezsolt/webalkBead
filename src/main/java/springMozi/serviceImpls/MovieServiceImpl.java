package springMozi.serviceImpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springMozi.dao.MovieDAO;
import springMozi.entities.ShowEntity;
import springMozi.entities.MovieEntity;
import springMozi.entities.ReservationEntity;
import springMozi.entities.UserEntity;
import springMozi.repositories.MovieRepository;
import springMozi.services.MovieService;
import springMozi.services.ReservationService;

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
	public void newShow(long id,ShowEntity newShow) {
		newShow.setSeatsToOne();
		movieRepository.findOne(id).getShows().add(newShow);
		movieRepository.save(movieRepository.findOne(id));	
	}

	@Override
	public void updateMovie(long id, MovieEntity updateEntity) {
		movieRepository.findOne(id).setMovieName(updateEntity.getMovieName());
		movieRepository.findOne(id).setAvailableCinemas(updateEntity.getAvailableCinemas());
		movieRepository.findOne(id).setMovieDuration(updateEntity.getMovieDuration());
		movieRepository.findOne(id).setMovieReleaseDate(updateEntity.getMovieReleaseDate());
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
		return getShowById(showId).getSeats();
	}
	
	@Override
	public void updateShow(long showId, ShowEntity updateShow) {		
		getShowById(showId).setCinemaName(updateShow.getCinemaName());
		getShowById(showId).setShowDate(updateShow.getShowDate());
		getShowById(showId).setShowRoom(updateShow.getShowRoom());
		getShowById(showId).setShowDimension(updateShow.getShowDimension());
		getShowById(showId).setTicketPrice(updateShow.getTicketPrice());
		
		getShowById(showId).setSeatsToOne();
		movieRepository.save(movieRepository.findOne(getShowById(showId).getMovieEntity().getId()));
	}

	@Override
	public void setSeats(long showId,int[][] seats, int newValue) {
		getShowById(showId).setSeats(seats,newValue);
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
	public List<MovieEntity> getMovieByCinemas(ArrayList<String> cinema) {
		List<MovieEntity> movieList =  movieDao.getAllMovie();
		List<MovieEntity> moviesByCinema = new ArrayList<MovieEntity>();

		for(int i=0;i<movieList.size();i++) {
			if(movieList.get(i).getAvailableCinemas().containsAll(cinema)){
				moviesByCinema.add(movieList.get(i));
			}		
		}

		return moviesByCinema;
	}
	

	@Override
	public ShowEntity getShowById(long showId) {
		ShowEntity show = null;
		for(MovieEntity me : movieRepository.findAll()) {
			for(int i=0;i<me.getShows().size();i++) {
				if(me.getShows().get(i).getId()==showId){
					show = me.getShows().get(i);
				}
			}
		}
		return show;
	}

	@Override
	public void deleteShow(long showId) {		
		for(MovieEntity me : movieRepository.findAll()) {
			for(int i=0;i<me.getShows().size();i++) {
				if(me.getShows().get(i).getId()==showId){
					me.getShows().remove(i);
					movieRepository.save(me);
				}
			}
		}
	}

	@Override
	public boolean checkForMovieName(String movieName) {
		boolean alreadyExist = false;
		for(MovieEntity e : movieRepository.findAll()) {
			if(e.getMovieName() == movieName) {
				alreadyExist = true;
			}
		}
		return alreadyExist;
	}

	@Override
	public boolean checkForShowId(long id) {
		boolean exists = false;
		for(MovieEntity me : movieRepository.findAll()) {
			for(int i=0;i<me.getShows().size();i++) {
				if(me.getShows().get(i).getId()==id){
					exists = true;
				}
			}
		}
		return exists;
	}

	@Override
	public void deleteReservationsByMovieId(long movieId, ReservationService reservationService,MovieService movieService) {
	
		for(ShowEntity a : showOne(movieId).getShows()) {
			for(ReservationEntity r : reservationService.listAllReservations()) {
				if(a.getId()==r.getShowId()) {
					reservationService.deleteReservationByShowId(a.getId(),movieService);				
				}
			}
		}
	}
		

}
