package com.ankit.trainTicketBooking.service;

import com.ankit.trainTicketBooking.entity.User;
import com.ankit.trainTicketBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service; // Use @Service, not @Component

import java.util.ArrayList;
import java.util.List;

@Service //  Use @Service for service components
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        User user = userRepository.findByUserid(userid);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with UserId:" + userid);
        }

        // Convert the role string from the database to a GrantedAuthority
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));

        return new org.springframework.security.core.userdetails.User(
                user.getUserid(),
                user.getPassword(),
                authorities
        );
    }
}
