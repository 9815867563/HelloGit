package com.collab.dao;

import java.util.List;

import com.collab.domain.User;

public interface UserDao {
	public boolean save(User user);

	public List<User> getAllUser();

	public User getUserById(int UserID);

	public boolean delete(int userId);
	public boolean update(User user);

	

	
	

}
