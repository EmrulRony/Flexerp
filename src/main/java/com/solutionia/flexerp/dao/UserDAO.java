package com.solutionia.flexerp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solutionia.flexerp.entity.User;

@Repository
public class UserDao {
	
	@Autowired
	private EntityManager em;
	
	public User addUser(User user) {
		Session session = em.unwrap(Session.class);
		session.persist(user);
		return user;
	}
	
	
	public List<User> findAllUser(){
		Session session = em.unwrap(Session.class);
		Query<User> query = session.createQuery("from User", User.class);
		return query.getResultList();
	}
	
	public User userExist(String email, String password) {

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password AND u.active = 1", User.class);

        query.setParameter("email", email);
        query.setParameter("password", password);
        try {
            User user = query.getSingleResult();
            System.out.println("User exists");
            return user;
        } catch (NoResultException e) {
            System.out.println("User doesn't exist");
            return null;
        }
    }
}
