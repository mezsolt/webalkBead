package springMozi.serviceImpls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springMozi.entities.UserEntity;
import springMozi.exceptions.BadRoleException;
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

	@Override
	public boolean checkForUsernameAndEmail(String username, String email) {
		boolean alreadyExist = false;
		for(UserEntity e : userRepository.findAll()) {
			if(e.getUsername() == username || e.getEmailAddress() == email) {
				alreadyExist = true;
			}
		}
		return alreadyExist;
	}

	@Override
	public boolean checkForId(long id) {
		boolean exists = false;
		for(UserEntity e : userRepository.findAll()) {
			if(e.getId() == id) {
				exists = true;
			}
		}
		return exists;
	}

	@Override
	public boolean roleCheck(ArrayList<String> roles) {
		boolean roleChecker = false;
		for(String s : roles) {
			if(!s.equals("USER") && !s.equals("ADMIN")) {
				roleChecker = true;
			}
		}
		return roleChecker;
	}
	
}
