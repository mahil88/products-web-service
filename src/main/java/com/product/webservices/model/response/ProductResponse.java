package com.product.webservices.model.response;


import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductResponse {

    private String productId;
    private String category;
    private String brand;
}
