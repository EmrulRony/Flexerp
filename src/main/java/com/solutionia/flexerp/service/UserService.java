package com.solutionia.flexerp.service;

import java.util.List;

import javax.transaction.Transactional;

import com.solutionia.flexerp.exceptions.EntityExistsException;
import com.solutionia.flexerp.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solutionia.flexerp.dao.UserDAO;
import com.solutionia.flexerp.entity.User;

@Service
public class UserService {
	
	@Autowired
    UserDAO userDao;
	
	@Transactional
	public User create(User user) throws EntityExistsException {
		user.setId(null);
		user.setActive(Boolean.TRUE);
		user.setUserType("admin");
			User _user = this.findUserByUserName(user.getLogin());
			if(_user != null){
				throw new EntityExistsException("User already exists");
			}
		return userDao.addUser(user);
	}
	
	@Transactional
	public User userExist(User user) throws EntityNotFoundException {
		User _user = userDao.userExist(user.getEmail(), user.getPassword());
		if (_user == null) {
			throw new EntityNotFoundException("Email and password mismatch");
		} else {
			return _user;
		}
	}

	@Transactional
	public User findUserByUserName(String userName) {
		return userDao.findUserByUserName(userName);
	}
	
	@Transactional
	public List<User> listUser(){
		return userDao.findAllUser();
	}
}
