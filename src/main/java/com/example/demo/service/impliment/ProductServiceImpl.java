package com.example.demo.service.impliment;

import com.example.demo.domain.model.MProduct;
import com.example.demo.repository.ECShopMapper;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ECShopMapper ProductMapper;

    @Override
    public List<MProduct> getProducts() {
        System.out.println("問題なし！");
        return ProductMapper.productFindMany();
    }

    @Override
    public void addProduct(MProduct product) {
        ProductMapper.productInsertOne(product);
    }

    @Override
    public MProduct getProduct(Integer code) {
        return ProductMapper.productFindOne(code);
    }

    @Override
    public void updateProductone(Integer code,
                               String name,
                               Integer price,
                               String gazou) {
        ProductMapper.productUpdate(code, name, price,gazou);
    }
    @Override
    public void deleteProductone(Integer code) {
        ProductMapper.productDelete(code);
    }
}