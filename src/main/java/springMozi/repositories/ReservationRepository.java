package springMozi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springMozi.entities.ReservationEntity;
import springMozi.entities.UserEntity;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity,Long>{
	List<ReservationEntity> findByUserId(long userId);
	List<ReservationEntity> findByMovieName(String movieName);
	List<ReservationEntity> findByCinemaName(String cinemaName);
}
