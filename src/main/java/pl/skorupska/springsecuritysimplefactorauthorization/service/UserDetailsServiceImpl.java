package pl.skorupska.springsecuritysimplefactorauthorization.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.skorupska.springsecuritysimplefactorauthorization.exception.UserNotExistException;
import pl.skorupska.springsecuritysimplefactorauthorization.repo.UserAppRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserAppRepo userAppRepo;

    public UserDetailsServiceImpl(UserAppRepo userAppRepo) {
        this.userAppRepo = userAppRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAppRepo.findByUsername(username).orElseThrow(() -> new UserNotExistException(username));
    }



}
