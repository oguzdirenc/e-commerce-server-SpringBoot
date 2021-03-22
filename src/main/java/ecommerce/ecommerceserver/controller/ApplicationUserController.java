package ecommerce.ecommerceserver.controller;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import ecommerce.ecommerceserver.payload.JWTLoginSuccessResponse;
import ecommerce.ecommerceserver.payload.LoginRequest;
import ecommerce.ecommerceserver.security.JwtTokenProvider;
import ecommerce.ecommerceserver.services.ApplicationUserService;
import ecommerce.ecommerceserver.services.MapValidationErrorService;
import ecommerce.ecommerceserver.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static ecommerce.ecommerceserver.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin
public class ApplicationUserController {

    private  final ApplicationUserService applicationUserService;
    private final MapValidationErrorService mapValidationErrorService;
    private final UserValidator userValidator;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/user/{id}")
    public ResponseEntity<ApplicationUser> name(@PathVariable String id){

        return new ResponseEntity<>(applicationUserService.getUserById(UUID.fromString(id)), HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){

        ResponseEntity <?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(result.hasFieldErrors()) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername()
                        ,loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTLoginSuccessResponse(true,jwt));

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody ApplicationUser user, BindingResult result){

        userValidator.validate(user,result);

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(result.hasFieldErrors()) return  errorMap;

        ApplicationUser newUser = applicationUserService.saveUser(user);

        return new ResponseEntity<ApplicationUser>(newUser, HttpStatus.CREATED);
    }

}
