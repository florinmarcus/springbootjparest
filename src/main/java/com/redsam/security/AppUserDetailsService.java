package com.redsam.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Component
public class AppUserDetailsService implements UserDetailsService {

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
       
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("AD_VP"));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, username + "1", authorities);
        System.out.println("loadUserByUsername :" + userDetails.getUsername() + " ---- " + userDetails.getPassword());
        return userDetails;
    }
}
