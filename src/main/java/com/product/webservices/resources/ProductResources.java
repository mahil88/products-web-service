package com.product.webservices.resources;

import com.product.webservices.model.Product;
import com.product.webservices.model.request.ProductRequest;
import com.product.webservices.model.response.ProductResponse;
import com.product.webservices.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ProductResources {

    private final ProductService productService;

    @PostMapping(path = "/products")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest){

        ProductResponse productResponse = productService.createProduct(productRequest);

        return new ResponseEntity<ProductResponse>(productResponse,HttpStatus.CREATED);
    }


    @GetMapping("/products/by-shopper")
    public List<ProductResponse> getProductsByShopper(
            @RequestParam String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false, defaultValue = "10" ) Integer limit) {

        return productService.getProductsByShopper(shopperId, category, brand, limit);
    }

}
