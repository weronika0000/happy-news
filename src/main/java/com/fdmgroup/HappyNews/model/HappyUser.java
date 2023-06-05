package com.fdmgroup.HappyNews.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="happy_users")
public class HappyUser {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name="username")
	private String username;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="role")
	private String role;
	@Column(name="petName")
	private String petName;
	
	public HappyUser() {
		super();
	}
	
	public HappyUser(String username) {
		this.username = username;
	}
	
	public HappyUser(String username, String email, String password) {
		
		this.username = username;
		this.email = email;
		this.password = hashingPassword(password);
	}

	public HappyUser(String username, String email, String password, String role, String petName) {
		
		this.username = username;
		this.email = email;
		this.password = hashingPassword(password);
		this.role = role;
		this.petName=petName;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
		this.password = hashingPassword(password);
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	@Override
	public String toString() {
		return "HappyUser [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", role=" + role + "]";
	}

	public String hashingPassword(String password) {
		Integer hashedPassword = password.hashCode();
		return hashedPassword.toString();
		
				
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, password, petName, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HappyUser other = (HappyUser) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(password, other.password) && Objects.equals(petName, other.petName)
				&& Objects.equals(role, other.role) && Objects.equals(username, other.username);
	}
	
	
}
