package com.product.webservices.model.request;


import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShelfItemDTO {
    @NotEmpty(message = "Product is Required")
    private String productId;
    @NotNull(message = "Relevancy Score is Required")
    private Double relevancyScore;

}
