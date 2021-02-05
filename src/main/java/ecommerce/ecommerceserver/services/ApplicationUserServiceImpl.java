package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.exceptions.NotFoundException;
import ecommerce.ecommerceserver.repositories.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;

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
    public ApplicationUser saveApplicationUser(ApplicationUser user) {

        return null;
    }

    @Override
    public String deleteApplicationUserById(UUID id) {
        return null;
    }
}
