package springMozi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springMozi.entities.MovieEntity;
import springMozi.entities.ReservationEntity;
import springMozi.entities.UserEntity;
import springMozi.exceptions.BadUserNameException;
import springMozi.serviceImpls.ReservationServiceImpl;
import springMozi.serviceImpls.UserServiceImpl;
import springMozi.services.ReservationService;
import springMozi.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;
	private ReservationService reservationService;
	
	@Autowired
	public UserController(UserServiceImpl userServiceImpl,ReservationServiceImpl reservationServiceImpl) {
		this.userService = userServiceImpl;
		this.reservationService = reservationServiceImpl;
	}
	
	@GetMapping(path="",produces=MediaType.APPLICATION_JSON_VALUE)
	Iterable<UserEntity> listUsers() {
		return userService.listAllUser();	
	}
	
	@PostMapping(path="",consumes=MediaType.APPLICATION_JSON_VALUE)
	void newUser(@RequestBody UserEntity newUser) {
		if(newUser.getFirstName().toUpperCase().equals("ADMIN")) {
			throw new BadUserNameException();
		}
		userService.newUser(newUser);
	}
	
	@PutMapping(path="/{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	void updateExisting(@PathVariable long id,@RequestBody UserEntity updateUser) {
		userService.updateUser(id,updateUser);
	}
	
	@DeleteMapping(path="/{id}")
	void deleteUser(@PathVariable long id) {
		for(ReservationEntity e : reservationService.listAllReservations()) {
			if(e.getUserId()==id) {
				reservationService.deleteReservation(e.getId());
			}
		}
		userService.deleteUser(id);
	}
	
	@GetMapping(path="/username",produces=MediaType.APPLICATION_JSON_VALUE)
	UserEntity findByUsername(@RequestParam String username) {
		return userService.findOneByUsername(username);
	}
	
	@GetMapping(path="/email",produces=MediaType.APPLICATION_JSON_VALUE)
	UserEntity findByEmailAddress(@RequestParam String email) {
		return userService.findOneByEmailAddress(email);
	}
	
	@GetMapping(path="/firstname",produces=MediaType.APPLICATION_JSON_VALUE)
	List<UserEntity> findByFirstName(@RequestParam String firstName) {
		return userService.findByFirstName(firstName);
	}
	
	@GetMapping(path="/lastname",produces=MediaType.APPLICATION_JSON_VALUE)
	List<UserEntity> findByLastName(@RequestParam String lastName) {
		return userService.findByLastName(lastName);
	}
	
	@GetMapping(path="/phonenumber",produces=MediaType.APPLICATION_JSON_VALUE)
	List<UserEntity> findByPhoneNumber(@RequestParam String phoneNumber) {
		return userService.findByPhoneNumber(phoneNumber);
	}
	
}
