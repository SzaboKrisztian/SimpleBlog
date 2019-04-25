package com.krisztianszabo.blog.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CustomAuthentication implements AuthenticationProvider {

  @Autowired
  private UserRepository userRepo;

  @Override
  public Authentication authenticate(Authentication auth)
      throws AuthenticationException {

    String username = auth.getName();
    String password = auth.getCredentials().toString();

    User user = userRepo.findByUsername(username);
    
    if(user==null){
      throw new BadCredentialsException("Username Not Found");
    }

    if(!password.equals(user.getPassword())){
      throw new BadCredentialsException("Username Or Password Is invalid");
    }

    List<SimpleGrantedAuthority> roles = new ArrayList<>();
    if(user.getRoles() != null) {
      for (Role role: user.getRoles()) {
        roles.add(new SimpleGrantedAuthority(role.getName()));
      }
    }

    return new UsernamePasswordAuthenticationToken(username, password, roles);
  }

  @Override
  public boolean supports(Class<?> arg0) {
    return true;
  }

}

