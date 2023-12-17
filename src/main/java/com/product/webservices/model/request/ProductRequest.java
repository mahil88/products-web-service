package com.product.webservices.model.request;


import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductRequest {

    private Long id;
    @NotEmpty(message = "Product is Required")
    private String productId;
    @NotEmpty(message = "Category is Required")
    private String category;
    @NotEmpty(message = "Brand is Required")
    private String brand;
}
