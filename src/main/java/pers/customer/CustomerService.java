package pers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pers.dao.OrderResponse;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private ICustomerRepo customerRepo;

    public ResponseEntity<Customer> add(Customer customer) {
        return ResponseEntity.ok(customerRepo.save(customer));
    }

    public ResponseEntity<Customer> findByName(String name) {
        return customerRepo.findByName(name)
                .map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> ResponseEntity.status(418).build());
    }

    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(customerRepo.findAll());
    }

    public ResponseEntity<List<OrderResponse>> getJoinInfo() {
        return ResponseEntity.ok(customerRepo.getJoinInfo());
    }
}
