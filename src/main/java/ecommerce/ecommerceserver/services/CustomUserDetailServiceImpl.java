package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import ecommerce.ecommerceserver.repositories.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserDetailServiceImpl implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ApplicationUser user = applicationUserRepository.findByUsername(s);

        if(user == null)  throw new  UsernameNotFoundException("Kullanıcı bulunamadı");
        return user;
    }

    @Transactional
    public ApplicationUser loadUserById(UUID userId){
        ApplicationUser user = applicationUserRepository.getByApplicationUserId(userId);
        if(user == null)  throw new  UsernameNotFoundException("Kullanıcı bulunamadı");
        return user;
    }
}
