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
import org.springframework.web.bind.annotation.RestController;

import springMozi.entities.MovieEntity;
import springMozi.entities.UserEntity;
import springMozi.exceptions.BadUserNameException;
import springMozi.serviceImpls.UserServiceImpl;
import springMozi.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserServiceImpl userServiceImpl) {
		this.userService = userServiceImpl;
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
	
	@GetMapping(path="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	UserEntity showOne(@PathVariable long id) {
		return userService.findUser(id);
	}
	
	@DeleteMapping(path="/{id}")
	void deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
	}
	
}
