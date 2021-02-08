package ecommerce.ecommerceserver.controller;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import ecommerce.ecommerceserver.services.ApplicationUserService;
import ecommerce.ecommerceserver.services.MapValidationErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ApplicationUserController {

    private  final ApplicationUserService applicationUserService;
    private final MapValidationErrorService mapValidationErrorService;

    @GetMapping("/user/{id}")
    public ResponseEntity<ApplicationUser> name(@PathVariable String id){

        return new ResponseEntity<>(applicationUserService.getUserById(UUID.fromString(id)), HttpStatus.OK);
    }
}
