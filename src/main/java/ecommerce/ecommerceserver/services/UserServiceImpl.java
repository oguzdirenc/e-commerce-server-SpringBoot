package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.User;
import ecommerce.ecommerceserver.exceptions.UsernameAlreadyExistsException;
import ecommerce.ecommerceserver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User newUser) {
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);
        }catch (Exception err){}
        throw new UsernameAlreadyExistsException("Kullanıcı adı '" + newUser.getUsername() +"' zaten kayıtlı");

    }
}
