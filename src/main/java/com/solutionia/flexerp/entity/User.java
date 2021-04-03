package com.solutionia.flexerp.entity;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */

@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByUserType", query = "SELECT u FROM User u WHERE u.userType = :userType"),
    @NamedQuery(name = "User.findByActive", query = "SELECT u FROM User u WHERE u.active = :active")})

public class User extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "login")
    @NotEmpty(message = "Login is a mandatory field, please provide login")
    @Size(min = 1, max = 64, message = "Length of login should not less than one")
    private String login;
    @Column(name = "email")
    @NotEmpty(message = "Email is a mandatory field, please provide email")
    @Size(min = 1, max = 64, message = "length of email should not less than one")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    @NotEmpty
    @Size(min = 4, message = "Password field character can't be less than 4")
    private String password;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "user_type")
    private String userType;
    @Basic(optional = false)
    @Column(name = "active")
    private Boolean active;
    @Lob
    @Column(name = "image")
    private byte[] image;

    public User() {
    }


    public User(String login, String password, String name, Boolean active) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.active = active;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
     public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "--- User[ id=" + super.getId() + " ] ---";
    }
    
}
