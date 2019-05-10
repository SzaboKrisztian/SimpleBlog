package com.krisztianszabo.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class UserRepository {
  @Autowired
  private JdbcTemplate jdbc;

  public User findByUsername(String name) {
    User result = null;
    String query = "SELECT * FROM Users WHERE username = '" + name + "';";
    SqlRowSet rs = jdbc.queryForRowSet(query);

    if(rs.first()) {
      result = new User();
      result.setId(rs.getInt("id"));
      result.setUserName(rs.getString("username"));
      result.setPassword(rs.getString("password"));
      result.setDisplayName(rs.getString("displayName"));
      result.setEmail(rs.getString("emailAddress"));

      Set<Role> roles = new HashSet<>();
      String roleQuery = "SELECT Roles.id, Roles.name FROM Users INNER JOIN users_to_roles ON " +
          "users_to_roles.user_id=Users.id INNER JOIN Roles ON Roles.id=users_to_roles.role_id " +
          "WHERE Users.id=" + result.getId();
      SqlRowSet role_rs = jdbc.queryForRowSet(roleQuery);
      while(role_rs.next()) {
        Role role = new Role();
        role.setId(role_rs.getInt("id"));
        role.setName(role_rs.getString("name"));
        roles.add(role);
      }
      result.setRoles(roles);
    }

    return result;
  }
}
