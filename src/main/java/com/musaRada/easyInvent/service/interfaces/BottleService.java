package com.musaRada.easyInvent.service.interfaces;

import com.musaRada.easyInvent.dto.in.BottleDtoIn;
import com.musaRada.easyInvent.model.Bottle;

import java.util.List;

public interface BottleService {

    Bottle addBottle(BottleDtoIn bottleDtoIn);

    Bottle findBottleByBarcode(String barcode);

    List<Bottle> findBottleByTitle(String title);

    String calculateBottle(Long bottleId,String currentWight);

}
