package springMozi.serviceImpls;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springMozi.entities.ReservationEntity;
import springMozi.repositories.ReservationRepository;
import springMozi.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{

	ReservationRepository reservationRepository;
	
	@Autowired
	public ReservationServiceImpl(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Override
	public Iterable<ReservationEntity> listAllReservations() {
		return this.reservationRepository.findAll();
	}

	@Override
	public void newReservation(ReservationEntity newReservation) {
		reservationRepository.save(newReservation);		
	}

	@Override
	public void deleteReservation(long id) {
		reservationRepository.delete(id);
	}

	@Override
	public ReservationEntity findReservation(long id) {
		return reservationRepository.findOne(id);
	}
	
	@Override
	public void updateReservation(long id, ReservationEntity updatedReservation) {
		this.reservationRepository.findOne(id).setMovieName(updatedReservation.getMovieName());
		this.reservationRepository.findOne(id).setCinemaName(updatedReservation.getCinemaName());
		this.reservationRepository.findOne(id).setShowRoom(updatedReservation.getShowRoom());
		this.reservationRepository.findOne(id).setShowDimension(updatedReservation.getShowDimension());
		this.reservationRepository.findOne(id).setShowDate(updatedReservation.getShowDate());
		this.reservationRepository.findOne(id).setSeats(updatedReservation.getSeats());
		
		this.reservationRepository.save(this.reservationRepository.findOne(id));
		
	}
	
	public List<ReservationEntity> findByUserId(long userId) {
		return reservationRepository.findByUserId(userId);
	}
	
	public List<ReservationEntity> findByCinemaName(String cinemaName) {
		return reservationRepository.findByCinemaName(cinemaName);
	}
	
	public List<ReservationEntity> findByMovieName(String movieName) {
		return reservationRepository.findByMovieName(movieName);
	}

	@Override
	public List<ReservationEntity> findByShowId(long showId) {
		return reservationRepository.findByShowId(showId);
	}

}
