package com.musaRada.easyInvent.repository;

import com.musaRada.easyInvent.model.Bottle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BottleRepository extends JpaRepository<Bottle,Long> {
    List<Bottle>findAllByBottleTitleContainingAndStatusIs(String title,String status);

    Optional<Bottle> findByBottleBarcodeIs(String bottleBarcode);
}
