package com.collab.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collab.dao.UserDao;
import com.collab.domain.User;

@RestController
public class UserRestCon {
	@Autowired
	UserDao userDao;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody User user) {
		userDao.save(user);
		return new ResponseEntity<String>("successfuly inserted", HttpStatus.OK);

	}

	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUser() {
		System.out.println("Hit***");
		List<User> listusers = userDao.getAllUser();
		return new ResponseEntity<List<User>>(listusers, HttpStatus.OK);

	}

	@RequestMapping(value = "/deleteUser/{UserID}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@PathVariable("UserID") int userId) {

		if (userDao.delete(userId)) {

			return new ResponseEntity<String>("successfuly deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Not successfuly deleted", HttpStatus.NOT_FOUND);

		}

	}

	@RequestMapping(value="/update/{UserId}",method=RequestMethod.PUT)
	public ResponseEntity<String>updateUser(@PathVariable("UserId") int userId,@RequestBody User user){
		
	{
		User curr_user =userDao.getUserById(userId);
		
		curr_user.setUserID(100);
		userDao.update(user);
		
		{
			
		
		return new ResponseEntity<String>("Successsfully updated",HttpStatus.OK);
		
		}
		
	}
	}
}

