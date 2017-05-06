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
		userRepository.findOne(id).setUsername(updatedUser.getUsername());
		userRepository.findOne(id).setPassword(updatedUser.getPassword());
		userRepository.findOne(id).setFirstName(updatedUser.getFirstName());
		userRepository.findOne(id).setLastName(updatedUser.getLastName());
		userRepository.findOne(id).setEmailAddress(updatedUser.getEmailAddress());
		userRepository.findOne(id).setPhoneNumber(updatedUser.getPhoneNumber());
		
		userRepository.save(userRepository.findOne(id));	
	}
	
	public UserEntity findOneByUsername(String username) {
		return userRepository.findOneByUsername(username);
	}
	
	public UserEntity findOneByEmailAddress(String emailAddress) {
		return userRepository.findOneByEmailAddress(emailAddress);
	}

	@Override
	public List<UserEntity> findByFirstName(String firstName) {
		return userRepository.findByFirstName(firstName);
	}

	@Override
	public List<UserEntity> findByLastName(String lastName) {
		return userRepository.findByLastName(lastName);
	}

	@Override
	public List<UserEntity> findByPhoneNumber(String phoneNumber) {
		return userRepository.findByPhoneNumber(phoneNumber);
	}
	
}
