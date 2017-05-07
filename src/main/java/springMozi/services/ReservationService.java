package springMozi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import springMozi.entities.ReservationEntity;

@Service
public interface ReservationService {

	Iterable<ReservationEntity> listAllReservations();
	void newReservation(ReservationEntity newReservation,int price);
	void deleteReservation(long id);
	ReservationEntity findReservation(long id);
	void updateReservation(long id,ReservationEntity updatedReservation,int price);
	void deleteReservationByUserId(long userId,MovieService movieService);
	void deleteReservationByShowId(long showId,MovieService movieService);
	
	//crudrepository
	List<ReservationEntity> findByUserId(long userId);
	List<ReservationEntity> findByShowId(long showId);
	List<ReservationEntity> findByCinemaName(String cinemaName);
	List<ReservationEntity> findByMovieName(String movieName);
	
}
