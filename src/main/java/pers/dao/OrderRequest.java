package pers.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.customer.Customer;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Customer customer;
}
