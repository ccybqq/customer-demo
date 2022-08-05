package pers.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.customer.Customer;
import pers.customer.CustomerService;
import pers.product.Product;
import pers.product.ProductService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
class OrderController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @PostMapping("/order/place")
    public Customer placeOrder(@RequestBody OrderRequest orderRequest) {
        Customer customer = orderRequest.getCustomer();
        customer.getProducts().forEach(p -> p.setCustomer(customer));
        return customerService.add(customer);
    }

    @PostMapping("/order/addProduct")
    public Product addProduct(@RequestBody Product product) {
        product.getCustomer().setProducts(List.of(product));
        return productService.add(product);
    }

    @PostMapping("/order/getCustomerByName")
    public String getCustomerByName(@RequestParam("name") String name) {
        return customerService.findByName(name).toString();
    }

    @PostMapping("/order/listCustomers")
    public List<Customer> getList() {
        return customerService.findAll();
    }
}
