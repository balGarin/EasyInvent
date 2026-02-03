package com.musaRada.easyInvent.repository;

import com.musaRada.easyInvent.enums.BottleStatus;
import com.musaRada.easyInvent.model.Bottle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BottleRepository extends JpaRepository<Bottle,Long> {
    List<Bottle>findAllByBottleTitleIgnoreCaseContainingAndStatusIs(String title,BottleStatus status);

    Optional<Bottle> findByBottleBarcodeIsAndStatusIs(String bottleBarcode,BottleStatus status);

    List<Bottle> findAllByStatusIsAndIdIn(BottleStatus status,List<Long>bottlesIds);
    List<Bottle> findAllByStatusIs(BottleStatus status);
}
