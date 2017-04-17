package springMozi.serviceImpls;

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
		// TODO Auto-generated method stub
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
		userRepository.findOne(id).setFirstName(updatedUser.getFirstName());
		userRepository.findOne(id).setLastName(updatedUser.getLastName());
		
		userRepository.save(userRepository.findOne(id));	
	}
	
}
