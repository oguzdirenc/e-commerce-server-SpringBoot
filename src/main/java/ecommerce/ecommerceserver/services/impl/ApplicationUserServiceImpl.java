package ecommerce.ecommerceserver.services.impl;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.ShoppingCart;
import ecommerce.ecommerceserver.exceptions.NotFoundException;
import ecommerce.ecommerceserver.exceptions.UsernameAlreadyExistsException;
import ecommerce.ecommerceserver.repositories.ApplicationUserRepository;
import ecommerce.ecommerceserver.request.UserUpdateRequest;
import ecommerce.ecommerceserver.services.ApplicationUserService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    
    @Autowired
    public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.applicationUserRepository = applicationUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
    public ApplicationUser saveUser(ApplicationUser newUser) {
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setConfirmPassword("");
            return applicationUserRepository.save(newUser);
        }catch (Exception err) {
            throw new UsernameAlreadyExistsException("Kullanıcı adı '" + newUser.getUsername() + "' zaten kayıtlı");
        }
    }

    @Override
    public ApplicationUser updateUser(String username, UserUpdateRequest userUpdateRequest) {
        ApplicationUser user = getUserByUsername(username);

        user.setFullName(userUpdateRequest.getFullName());
        user.setUserPhoneNumber(userUpdateRequest.getPhoneNumber());
        user.setUserAddress(userUpdateRequest.getAddress());

        return applicationUserRepository.save(user);
    }


    @Override
    public ApplicationUser getUserByUsername(String username) {
        return applicationUserRepository.findByUsername(username);
    }

    @Override
    public ShoppingCart getShoppingCartByUsername(String username) {
        ApplicationUser user = getUserByUsername(username);

        return user.getShoppingCart();
    }

}
