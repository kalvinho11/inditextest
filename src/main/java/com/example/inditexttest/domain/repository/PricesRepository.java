package com.example.inditexttest.domain.repository;

import com.example.inditexttest.domain.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface PricesRepository extends JpaRepository<PriceEntity, Integer> {

    @Query("SELECT * FROM PRICES p" +
            " WHERE p.startDate < :orderDateTime" +
            " AND p.endDate > :orderDateTime" +
            " AND p.brandId = :brandId" +
            " AND p.productId = :productId")
    Collection<PriceEntity> findCorrectPriceInDate(@Param("orderDateTime") LocalDateTime orderDateTime,
                                                   @Param("brandId") Integer brandId,
                                                   @Param("productId") Integer productId);
}
