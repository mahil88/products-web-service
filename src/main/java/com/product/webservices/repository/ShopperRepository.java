package com.product.webservices.repository;

import com.product.webservices.model.Shopper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopperRepository extends JpaRepository<Shopper,Long> {

    @Query("SELECT si.shopper FROM ShelfItem si WHERE si.product.productId = :productId")
    List<Shopper> findShoppersByProduct(@Param("productId") String productId, Pageable pageable);

}
