package ecommerce.ecommerceserver.services;

import ecommerce.ecommerceserver.request.OrderRequest;

public interface OrderService {

    String saveOrder(OrderRequest orderRequest,String username);
}
