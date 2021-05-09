package ecommerce.ecommerceserver.services.impl;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import ecommerce.ecommerceserver.domain.Book;
import ecommerce.ecommerceserver.domain.BookOrder;
import ecommerce.ecommerceserver.domain.Cargo;
import ecommerce.ecommerceserver.repositories.BookOrderRepository;
import ecommerce.ecommerceserver.repositories.BookRepository;
import ecommerce.ecommerceserver.repositories.CargoRepository;
import ecommerce.ecommerceserver.request.OrderRequest;
import ecommerce.ecommerceserver.services.ApplicationUserService;
import ecommerce.ecommerceserver.services.BookService;
import ecommerce.ecommerceserver.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ApplicationUserService applicationUserService;
    private final BookService bookService;
    private final BookOrderRepository bookOrderRepository;
    private final CargoRepository cargoRepository;



    @Override
    public String saveOrder(OrderRequest orderRequest, String username) {

        var user = applicationUserService.getUserByUsername(username);
        var orderBooks = user.getShoppingCart().getShoppingCartBooks();

        for(Book book : orderBooks){
            if(book.getBookStock()<=0){
                return "Yeterli stok bulunmamaktadır.";
            }
        }

        for(Book book : orderBooks){
            book.setBookStock(book.getBookStock()-1);
            bookService.saveBook(book);
        }
        Cargo cargo = new Cargo();
        cargo.setCargoPrice(orderRequest.getCargoPrice());
        cargoRepository.save(cargo);


        BookOrder order = BookOrder.builder()
                .username(username)
                .buyerName(orderRequest.getName())
                .buyerPhone(orderRequest.getPhone())
                .deliveryAddress(orderRequest.getDeliveryAddress())
                .invoiceAddress(orderRequest.getInvoiceAddress())
                .orderTotalPrice(orderRequest.getTotalPrice())
                .cargo(cargo)
                .build();

        bookOrderRepository.save(order);


        return "Sipariş Oluşturuldu";
    }
}
