package com.musaRada.easyInvent.service.interfaces;

import com.musaRada.easyInvent.dto.in.BottleDtoIn;
import com.musaRada.easyInvent.dto.in.RequestCalculateBottle;
import com.musaRada.easyInvent.dto.out.BottleDtoOut;
import com.musaRada.easyInvent.model.Bottle;

import java.util.List;

public interface BottleService {

    BottleDtoOut addBottle(BottleDtoIn bottleDtoIn);

    BottleDtoOut findBottleByBarcode(String barcode);

    List<BottleDtoOut> findBottleByTitle(String title);

    String calculateBottle(RequestCalculateBottle requestCalculateBottle);


    String approveBottle(List<Long>bottlesIds);

    List<Bottle> getAllWaitingBottles();

    String removeBottle(Long bottleId);

    List<Bottle> getAllBottles(Integer from,Integer limit);

    BottleDtoOut updateBottle(Long bottleId, BottleDtoIn bottleDtoIn);
}
