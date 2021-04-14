package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.ShoppingCart;
import ecommerce.ecommerceserver.exceptions.NotFoundException;
import ecommerce.ecommerceserver.exceptions.UsernameAlreadyExistsException;
import ecommerce.ecommerceserver.repositories.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public ApplicationUser getUserById(UUID id) {

        return null;

    }

    @Override
    public Set<Book> getFavoriteBooksByUserId(UUID id) {

        ApplicationUser applicationUser= applicationUserRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("User Not Found"));

        return applicationUser.getFavoriteBookSet();
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

    @Override
    public ApplicationUser saveApplicationUser(ApplicationUser user) {

        return null;
    }

    @Override
    public String deleteApplicationUserById(UUID id) {
        return null;
    }
}
