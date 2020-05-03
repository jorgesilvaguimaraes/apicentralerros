package br.com.apicentralerros.domain.service.impl;

import br.com.apicentralerros.domain.entity.User;
import br.com.apicentralerros.domain.repository.UserRepository;
import br.com.apicentralerros.domain.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface, UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).get();
    }

    @Override
	public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
