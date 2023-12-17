package com.product.webservices.resources;

import com.product.webservices.model.request.ShoppersRequest;
import com.product.webservices.model.response.ShopperResponse;
import com.product.webservices.service.ShopperService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ShopperResources {

    private final ShopperService shopperService;


    @PostMapping(path = "/shoppers")
    public ResponseEntity<ShopperResponse> createShoppers(@Valid @RequestBody ShoppersRequest shoppersRequest){

        ShopperResponse shopperResponse = shopperService.createShopper(shoppersRequest);

        return new ResponseEntity<ShopperResponse>(shopperResponse,HttpStatus.CREATED);
    }

    @GetMapping("/shoppers/by-product")
    public List<ShopperResponse> getShoppersByProduct(
            @RequestParam String productId,
            @RequestParam(required = false, defaultValue = "10") Integer limit) {
        List<ShopperResponse> shoppers = shopperService.getShoppersByProduct(productId, limit);
        return shoppers;
    }

}
