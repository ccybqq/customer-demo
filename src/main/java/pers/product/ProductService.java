package pers.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private IProductRepo productRepo;

    public Product add(Product product) {
        System.out.println("productService::add called.");
        return productRepo.save(product);
    }
}
