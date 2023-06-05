package com.fdmgroup.HappyNews.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fdmgroup.HappyNews.model.HappyUser;

public class HappyUserDetails implements UserDetails{
	private final HappyUser happyUser;
	
	
	public HappyUserDetails(HappyUser happyUser) {
		super();
		this.happyUser = happyUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.singletonList(new SimpleGrantedAuthority(happyUser.getRole()));
	}

	@Override
	public String getPassword() {
		
		return this.happyUser.getPassword();
	}
	
	public void setPassword(String password) {
		this.happyUser.setPassword(password);
	}

	@Override
	public String getUsername() {
		
		return this.happyUser.getUsername();
	}
	
	public String getEmail() {
		return this.happyUser.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	// need for receiving data of authenticated user
		public HappyUser getHappyUser() {
			return this.happyUser;
		}
		
	public String getPetName() {
		return this.happyUser.getPetName();
	}

}
