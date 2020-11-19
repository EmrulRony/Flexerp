package com.solutionia.flexerp.rest.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solutionia.flexerp.entity.User;
import com.solutionia.flexerp.service.UserService;

@RestController
@CrossOrigin
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
    public ResponseEntity<?> isUserExist (@RequestBody User user) {
    	User usr = userService.userExist(user);
    	if (usr == null) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	} else {
    		return new ResponseEntity<User>(usr,HttpStatus.OK);
    	}
    }
    
    @PostMapping("/login/register")
    public User createUser (@RequestBody User user) {
    	return userService.create(user);
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