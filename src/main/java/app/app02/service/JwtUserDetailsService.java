package app.app02.service;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.app02.model.JwtRequest;

@Service
public class JwtUserDetailsService implements UserDetailsService {
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    JwtRequest uRequest = new JwtRequest("ekosutrisno", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6");

    if (uRequest.getUsername().equals(username)) {
      return new User(uRequest.getUsername(), uRequest.getPassword(), new ArrayList<>());
    } else {
      throw new UsernameNotFoundException("User tidak ditemukan dengan nama: " + username);
    }
  }
}