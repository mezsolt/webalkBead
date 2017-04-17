package springMozi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springMozi.entities.MovieEntity;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Long> {

}
