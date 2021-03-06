package springMozi.services;

import java.util.ArrayList;
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
	boolean checkForUsernameAndEmail(String username,String email);
	boolean checkForId(long id);
	boolean roleCheck(ArrayList<String> roles);
	
	//crudrepository
	UserEntity findOneByUsername(String username);
	UserEntity findOneByEmailAddress(String emailAddress);
	List<UserEntity> findByFirstName(String firstName);
	List<UserEntity> findByLastName(String lastName);
	List<UserEntity> findByPhoneNumber(String phoneNumber);
	
}
