package com.mystore.product.service.impl;

import com.mystore.product.model.Product;
import com.mystore.product.repo.ProductRepo;
import com.mystore.product.dto.ProductDTO;
import com.mystore.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product>productList = productRepo.findAll();
        return modelMapper.map(productList, new TypeToken<List<ProductDTO>>(){}.getType());
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        productRepo.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        productRepo.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    @Override
    public String deleteProduct(Integer productId) {
        productRepo.deleteById(productId);
        return "Product deleted";
    }

    @Override
    public ProductDTO getProductByProductId(Integer productId) {
        Product product = productRepo.getProductById(productId);
        return modelMapper.map(product, ProductDTO.class);
    }
}
