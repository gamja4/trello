package com.gamja.trello.security.service;

import com.gamja.trello.common.exception.ErrorCode;
import com.gamja.trello.entity.User;
import com.gamja.trello.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(ErrorCode.USERNAME_NOT_FOUND.getMsg())
        );

        return new UserDetailsImpl(user);
    }
}
