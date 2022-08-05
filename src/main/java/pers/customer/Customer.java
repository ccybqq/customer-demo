package pers.customer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.product.Product;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_details")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_details_sequence", allocationSize = 1)
    @GeneratedValue(generator = "customer_details_sequence", strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    //@JsonManagedReference
    private List<Product> products;
}
