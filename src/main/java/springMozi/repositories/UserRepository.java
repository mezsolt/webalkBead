package springMozi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springMozi.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	List<UserEntity> findByUserName(String userName);
	List<UserEntity> findByEmailAddress(String emailAddress);
}
