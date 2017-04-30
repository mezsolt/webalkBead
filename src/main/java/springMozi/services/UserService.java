package springMozi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import springMozi.entities.UserEntity;

@Service
public interface UserService {

	Iterable<UserEntity> listAllUser();
	void newUser(UserEntity newUser);
	void deleteUser(long id);
	UserEntity findUser(long id);
	void updateUser(long id,UserEntity updatedUser);
	UserEntity showOne(long id);
	List<UserEntity> findByUserName(String userName);
	List<UserEntity> findByEmailAddress(String emailAddress);
	
}
