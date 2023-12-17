package com.product.webservices.service;

import com.product.webservices.exception.ResourceAlreadyExistsException;
import com.product.webservices.model.Product;
import com.product.webservices.model.request.ProductRequest;
import com.product.webservices.model.response.ProductResponse;
import com.product.webservices.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest) {
        if (productRepository.findByProductId(productRequest.getProductId()).isPresent()) {
            throw new ResourceAlreadyExistsException("Product already exists: " + productRequest.getProductId());
        }

        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productRequest, Product.class);
        Product createdProduct = productRepository.save(product);
        return modelMapper.map(createdProduct, ProductResponse.class);
    }

    public List<ProductResponse> getProductsByShopper(String shopperId, String category, String brand, Integer limit) {
        List<ProductResponse> productResponses = null;
        List<Product> products = null;
        if (limit == null || limit < 1 || limit > 100) {
            throw new ValidationException("Limit is invalid");
        }

        Pageable pageable = PageRequest.of(0, limit);
        ModelMapper modelMapper = new ModelMapper();

        if (category != null && brand != null) {
            products= productRepository.findProductsByShopperIdAndCategoryAndBrand(shopperId, category, brand,pageable);
        } else if (category != null) {
            products= productRepository.findProductsByShopperIdAndCategory(shopperId, category,pageable);
        } else if (brand != null) {
            products= productRepository.findProductsByShopperIdAndBrand(shopperId, brand,pageable);
        } else {
            products= productRepository.findProductsByShopperId(shopperId,pageable);
        }
        productResponses = products.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
        return  productResponses;
    }

}
