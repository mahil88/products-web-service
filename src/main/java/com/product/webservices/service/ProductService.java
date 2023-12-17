package com.product.webservices.service;

import com.product.webservices.model.Product;
import com.product.webservices.model.request.ProductRequest;
import com.product.webservices.model.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productDto);
    List<ProductResponse> getProductsByShopper(String shopperId, String category, String brand, Integer limit);

}
