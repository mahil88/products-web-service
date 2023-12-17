package com.product.webservices.service;

import com.product.webservices.exception.ResourcesNotFoundException;
import com.product.webservices.model.Product;
import com.product.webservices.model.ShelfItem;
import com.product.webservices.model.Shopper;
import com.product.webservices.model.request.ShelfItemDTO;
import com.product.webservices.model.request.ShoppersRequest;
import com.product.webservices.model.response.ProductResponse;
import com.product.webservices.model.response.ShopperResponse;
import com.product.webservices.repository.ProductRepository;
import com.product.webservices.repository.ShopperRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class ShopperServiceImpl implements ShopperService {

   private final ProductRepository productRepository;
   private final ShopperRepository shopperRepository;
    @Override
    @Transactional
    public ShopperResponse createShopper(ShoppersRequest shoppersRequest) {
        Shopper personalizedInfo = new Shopper();
        personalizedInfo.setShopperId(shoppersRequest.getShopperId());
        List<ShelfItem> shelfItems = new ArrayList<>();
        List<ShelfItemDTO> shelfItemDTOS = shoppersRequest.getShelf();

        for (ShelfItemDTO shelfItemDTO : shelfItemDTOS) {
            ShelfItem shelfItem = new ShelfItem();

            Product product = productRepository.findByProductId(shelfItemDTO.getProductId())
                    .orElseThrow(() -> new ResourcesNotFoundException("Product not found: " + shelfItemDTO.getProductId()));

            shelfItem.setProduct(product);
            shelfItem.setRelevancyScore(shelfItemDTO.getRelevancyScore());

            shelfItems.add(shelfItem);
        }

        personalizedInfo.setShelf(shelfItems);
        ModelMapper modelMapper = new ModelMapper();
        Shopper shopper = shopperRepository.save(personalizedInfo);
        return modelMapper.map(shopper, ShopperResponse.class);
    }

    public List<ShopperResponse> getShoppersByProduct(String productId, Integer limit) {
        if (limit == null || limit < 1 || limit > 1000) {
            throw new ValidationException("Limit is invalid");
        }

        Pageable pageable = PageRequest.of(0, limit);
        List<Shopper> shoppers = shopperRepository.findShoppersByProduct(productId, pageable);
        return shoppers.stream()
                .map(shopper -> new ShopperResponse(shopper.getShopperId()))
                .collect(Collectors.toList());

    }
}
