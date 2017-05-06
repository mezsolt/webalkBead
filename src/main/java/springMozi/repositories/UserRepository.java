package springMozi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springMozi.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findOneByUsername(String username);
	UserEntity findOneByEmailAddress(String emailAddress);
	List<UserEntity> findByFirstName(String firstName);
	List<UserEntity> findByLastName(String lastName);
	List<UserEntity> findByPhoneNumber(String phoneNumber);
}
