package com.product.webservices.service;

import com.product.webservices.model.Shopper;
import com.product.webservices.model.request.ShoppersRequest;
import com.product.webservices.model.response.ShopperResponse;

import java.util.List;

public interface ShopperService {
    ShopperResponse createShopper(ShoppersRequest shoppersRequest);
    List<ShopperResponse> getShoppersByProduct(String productId, Integer limit);
}
