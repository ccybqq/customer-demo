package pers.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pers.dao.OrderResponse;

import java.util.List;
import java.util.Optional;

interface ICustomerRepo extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByName(String name);

    //@Query(value = "SELECT customer_details.name AS customerName, product_details.name AS productName FROM customer_details JOIN product_details", nativeQuery = true)
    @Query(value = "SELECT new pers.dao.OrderResponse(c.name, p.name) FROM Customer c JOIN c.products p")
    List<OrderResponse> getJoinInfo();
}
