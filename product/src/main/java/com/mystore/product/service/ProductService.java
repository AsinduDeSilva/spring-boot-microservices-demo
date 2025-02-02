package com.mystore.product.service;

import com.mystore.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    ProductDTO saveProduct(ProductDTO productDTO);

    ProductDTO updateProduct(ProductDTO productDTO);

    String deleteProduct(Integer productId);

    ProductDTO getProductById(Integer productId);
}
