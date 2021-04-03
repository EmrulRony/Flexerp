package com.solutionia.flexerp.controller.login;

import java.util.List;

import com.solutionia.flexerp.exceptions.EntityExistsException;
import com.solutionia.flexerp.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solutionia.flexerp.entity.User;
import com.solutionia.flexerp.service.UserService;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/api")
public class LoginController {
	private User user;
	
	@Autowired
	private UserService userService;

    @GetMapping("/test")
    public String testController(){
        return "<p>login controller test success</p>";
    }
    
    @PostMapping("/login/verifyuser")
    public ResponseEntity<Object> isUserExist (@RequestBody User user) throws EntityNotFoundException {
//    	try {
            User _user = userService.userExist(user);
            return new ResponseEntity<>(_user, HttpStatus.OK);
//        } catch (UserNotFoundException ex){
//    	    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
//        }
    }
    
    @PostMapping("/login/register")
    public ResponseEntity createUser (@RequestBody @Valid User user) {
        try {
            User _user = userService.create(user);
            return new ResponseEntity(_user, HttpStatus.CREATED);
        } catch (EntityExistsException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
        }
    }

    @GetMapping("/login/users")
    public List<User> findAllUser (){
    	return userService.listUser();
    }
    
    @GetMapping("/login/getUserJson")
    public User findUserJson() {
    	return new User();
    }
    
}