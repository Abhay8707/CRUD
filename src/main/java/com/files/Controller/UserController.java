package com.files.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.files.Entities.User;
import com.files.Exception.UserNotFoundException;
import com.files.Repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController 
{
	@Autowired
	private UserRepository ur;
	
	@PostMapping("/user")
	public User newUser(@RequestBody User newUser)
	{
		return ur.save(newUser);
	}
	
	@GetMapping("/getusers")
	public List<User> getAllUsers()
	{
		return ur.findAll();
	}
	
	@GetMapping("/user/{id}")
	public User getUserByID(@PathVariable Long id)
	{
		return ur.findById(id)
				.orElseThrow(()->new UserNotFoundException(id));
	}
	
	@PutMapping("/user/{id}")
	public User updateuser(@RequestBody User newUser,@PathVariable Long id)
	{
		return ur.findById(id)
				.map(user -> {
					user.setUsername(newUser.getUsername());
					user.setName(newUser.getName());
					user.setEmail(newUser.getEmail());
					return ur.save(user);
				}).orElseThrow(()->new UserNotFoundException(id));
	}
	
	@DeleteMapping("/user/{id}")
	public String deleteuser(@PathVariable Long id)
	{
		if(!ur.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		ur.deleteById(id);
		return "User with id "+id+" has been deleted success";
	}
}
