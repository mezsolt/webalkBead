package springMozi.serviceImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springMozi.entities.UserEntity;
import springMozi.repositories.UserRepository;
import springMozi.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public Iterable<UserEntity> listAllUser() {
		return userRepository.findAll();
	}

	@Override
	public void newUser(UserEntity newUser) {
		userRepository.save(newUser);		
	}

	@Override
	public void deleteUser(long id) {
		userRepository.delete(id);
	}

	public UserEntity findUser(long id) {
		return userRepository.findOne(id);
	}
			
	public void updateUser(long id,UserEntity updatedUser) {	
		userRepository.findOne(id).setUserName(updatedUser.getUserName());
		userRepository.findOne(id).setPassword(updatedUser.getPassword());
		userRepository.findOne(id).setFirstName(updatedUser.getFirstName());
		userRepository.findOne(id).setLastName(updatedUser.getLastName());
		userRepository.findOne(id).setEmailAddress(updatedUser.getEmailAddress());
		userRepository.findOne(id).setPhoneNumber(updatedUser.getPhoneNumber());
		
		userRepository.save(userRepository.findOne(id));	
	}

	@Override
	public UserEntity showOne(long id) {
		return userRepository.findOne(id);
	}
	
	public List<UserEntity> findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	
	public List<UserEntity> findByEmailAddress(String emailAddress) {
		return userRepository.findByEmailAddress(emailAddress);
	}
	
}
