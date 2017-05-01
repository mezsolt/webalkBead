package springMozi.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springMozi.entities.CinemaDateAndSeats;
import springMozi.entities.MovieEntity;
import springMozi.entities.UserEntity;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Long> {
	List<MovieEntity> findByMovieName(String movieName);

}
