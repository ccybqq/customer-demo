package pers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private ICustomerRepo customerRepo;

    public Customer add(Customer customer) {
        System.out.println("customerService::add called.");
        return customerRepo.save(customer);
    }

    public Customer findByName(String name) {
        return customerRepo.findByName(name);
    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }
}
