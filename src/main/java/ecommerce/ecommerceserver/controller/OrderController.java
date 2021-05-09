package ecommerce.ecommerceserver.controller;

import ecommerce.ecommerceserver.request.OrderRequest;
import ecommerce.ecommerceserver.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<?> saveOrder(@Valid Principal principal, @RequestBody OrderRequest orderRequest){
        return new ResponseEntity<>(orderService.saveOrder(orderRequest, principal.getName()), HttpStatus.OK);
    }
}
