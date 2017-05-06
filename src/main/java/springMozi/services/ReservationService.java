package springMozi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import springMozi.entities.ReservationEntity;

@Service
public interface ReservationService {

	Iterable<ReservationEntity> listAllReservations();
	void newReservation(ReservationEntity newReservation);
	void deleteReservation(long id);
	ReservationEntity findReservation(long id);
	void updateReservation(long id,ReservationEntity updatedReservation);
	
	//crudrepository
	List<ReservationEntity> findByUserId(long userId);
	List<ReservationEntity> findByShowId(long showId);
	List<ReservationEntity> findByCinemaName(String cinemaName);
	List<ReservationEntity> findByMovieName(String movieName);
	
}
