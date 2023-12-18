package com.anonymous.privacytool.config;

import com.anonymous.privacytool.entity.User;
import com.anonymous.privacytool.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AuthUserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByEmail(username);
        Set<GrantedAuthority> setAuths=new HashSet<>();
        List<GrantedAuthority> grantedAuthorityList=new ArrayList<>(setAuths);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.get().getEmail())
                .password(user.get().getPassword())
                .authorities(grantedAuthorityList)
                .build();
    }
}
