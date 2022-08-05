package pers.product;

import org.springframework.data.jpa.repository.JpaRepository;

interface IProductRepo extends JpaRepository<Product, Integer> {
}
