package com.krisztianszabo.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private CustomAuthentication customAuthentication;

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.csrf().disable();

    //Login,logout page and resources are permitted for all users
    http.authorizeRequests().antMatchers("/","/login","/logout","/resources/**","/blog","/css/**","/blog/entry/**").permitAll();


    //userInfo page requires login as ROLE_USER or ROLE_ADMIN.
    // If no login, it will redirect to /login page.
    //http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('User', 'Admin')");

    // For ADMIN only.
    //http.authorizeRequests().antMatchers("/admin").access("hasRole('Admin')");

    //Login and logout configurations
    //username and password parameter must match the login form username and password parameter
    //When the user logs out,it will be redirected to login page as specified,it is always good practice to display a logout message whwn the user logs out,To display a logout message,follow the last snippet.
    //On Successful login user will be redirected to "/index" page as specified below else back to login page.
    http.authorizeRequests().and().formLogin().loginProcessingUrl("/login").loginPage("/login")
        .defaultSuccessUrl("/").failureUrl("/login?error=true")
        .usernameParameter("username").passwordParameter("password")
        .and()
        .logout().logoutSuccessUrl("/login?logout");


    // If no login, it will redirect to /login page.
    http.authorizeRequests().antMatchers("/**").authenticated();

    //Handling Access Denied Request
    http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/accessdenied");
  }

  @Autowired
  public void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(customAuthentication);
  }
}