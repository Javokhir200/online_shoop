package uz.lee.onlineshoop.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.repository.UserEntityRepository;

import java.util.List;

@Service
public class CustomUserDetails implements UserDetailsService {

    private final UserEntityRepository userRepository;

    public CustomUserDetails(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User is not exist"));
    }
}
