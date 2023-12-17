package com.product.webservices.repository;

import com.product.webservices.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByProductId(String productId);

    @Query("SELECT si.product FROM ShelfItem si WHERE si.shopper.shopperId = :shopperId and si.product.category=:category and si.product.brand=:brand ")
    List<Product> findProductsByShopperIdAndCategoryAndBrand(@Param("shopperId") String shopperId, String category, String brand, Pageable pageable);

    @Query("SELECT si.product FROM ShelfItem si WHERE si.shopper.shopperId = :shopperId and si.product.category=:category")
    List<Product> findProductsByShopperIdAndCategory(@Param("shopperId") String shopperId, String category, Pageable pageable);

    @Query("SELECT si.product FROM ShelfItem si WHERE si.shopper.shopperId = :shopperId and si.product.brand=:brand")
    List<Product> findProductsByShopperIdAndBrand(@Param("shopperId") String shopperId, String brand, Pageable pageable);

    @Query("SELECT si.product FROM ShelfItem si WHERE si.shopper.shopperId = :shopperId ")
    List<Product> findProductsByShopperId(@Param("shopperId") String shopperId, Pageable pageable);

}
