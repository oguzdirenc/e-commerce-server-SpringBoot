package ecommerce.ecommerceserver.controller;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import ecommerce.ecommerceserver.payload.JWTLoginSuccessResponse;
import ecommerce.ecommerceserver.payload.LoginRequest;
import ecommerce.ecommerceserver.request.UserUpdateRequest;
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
import java.security.Principal;
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

    @GetMapping("/profile")
    public ResponseEntity<ApplicationUser> getUserByName(Principal principal){
        return new ResponseEntity<>(applicationUserService.getUserByUsername(principal.getName()),HttpStatus.OK);
    }

    @PostMapping    ("/update")
    public ResponseEntity<ApplicationUser> updateUser(Principal principal, @RequestBody UserUpdateRequest userUpdateRequest){
        return new ResponseEntity<>(applicationUserService.updateUser(principal.getName(),userUpdateRequest),HttpStatus.OK);
    }

}
