package com.example.inditexttest.domain.repository;

import com.example.inditexttest.domain.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<Price, Integer> {

    @Query(value = "SELECT * FROM PRICES p" +
            " WHERE p.START_DATE < :orderDateTime" +
            " AND p.END_DATE > :orderDateTime" +
            " AND p.BRAND_ID = :brandId" +
            " AND p.PRODUCT_ID = :productId", nativeQuery = true)
    List<Price> findCorrectPriceInDate(@Param("orderDateTime") Date orderDateTime,
                                       @Param("brandId") Integer brandId,
                                       @Param("productId") Integer productId);

    Long countPriceByProductId(@Param("productId") Integer productId);
}
