package springMozi.services;

import org.springframework.stereotype.Service;

import springMozi.entities.UserEntity;

@Service
public interface UserService {

	Iterable<UserEntity> listAllUser();
	void newUser(UserEntity newUser);
	void deleteUser(long id);
	UserEntity findUser(long id);
	void updateUser(long id,UserEntity updatedUser);
	
}
