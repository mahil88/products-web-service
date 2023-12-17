package com.product.webservices.model.request;


import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShoppersRequest {

    @NotEmpty(message = "Shopper is required")
    private String shopperId;

    @NotEmpty(message = "At least one item in the shelf is required")
    @Valid
    private List<ShelfItemDTO> shelf;




}
