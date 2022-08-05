package pers.customer;

import org.springframework.data.jpa.repository.JpaRepository;

interface ICustomerRepo extends JpaRepository<Customer, Integer> {
    Customer findByName(String name);
}
