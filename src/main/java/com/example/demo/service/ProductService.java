package com.example.demo.service;
import com.example.demo.domain.model.MProduct;
import java.util.List;
public interface ProductService
{
        List<MProduct> getProducts();
        void addProduct(MProduct product);

        MProduct getProduct(Integer code);
        void updateProductone(Integer code,
                                   String name,
                                     Integer price,
                                     String gazou
                                     );
        void deleteProductone(Integer code);
}
