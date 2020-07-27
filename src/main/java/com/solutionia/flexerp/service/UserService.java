package com.solutionia.flexerp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solutionia.flexerp.dao.UserDao;
import com.solutionia.flexerp.entity.User;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	@Transactional
	public User create(User user) {
		user.setId(null);
		user.setActive(Boolean.TRUE);
		user.setUserType("admin");
		if (user.getLogin().equals("") || user.getLogin() == null) {
			return null;
		}
		else if (user.getPassword().equals("") || user.getPassword() == null) {
			return null;
		}
		return userDao.addUser(user);
	}
	
	@Transactional
	public User userExist(User user) {
		return userDao.userExist(user.getLogin(), user.getPassword());
	}
	
	@Transactional
	public List<User> listUser(){
		return userDao.findAllUser();
	}
}
