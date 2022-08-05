package pers.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.customer.Customer;
import pers.customer.CustomerService;
import pers.product.Product;
import pers.product.ProductService;

import java.util.List;

@RestController
class OrderController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @PostMapping("/order/place")
    public ResponseEntity<Customer> placeOrder(@RequestBody OrderRequest orderRequest) {
        Customer customer = orderRequest.getCustomer();
        customer.getProducts().forEach(p -> p.setCustomer(customer));
        return customerService.add(customer);
    }

    @PostMapping("/order/getCustomerByName")
    public ResponseEntity<Customer> getCustomerByName(@RequestParam("name") String name) {
        return customerService.findByName(name);
    }

    @PostMapping("/order/listCustomers")
    public ResponseEntity<List<Customer>> getList() {
        return customerService.findAll();
    }

    @PostMapping("/order/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        product.getCustomer().setProducts(List.of(product));
        return productService.add(product);
    }

    @PostMapping("/order/join")
    public ResponseEntity<List<OrderResponse>> getJoinInfo() {
        return customerService.getJoinInfo();
    }
}
