package pers.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private IProductRepo productRepo;

    public ResponseEntity<Product> add(Product product) {
        return ResponseEntity.ok(productRepo.save(product));
    }
}
