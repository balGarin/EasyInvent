package com.musaRada.easyInvent.service.services;

import com.musaRada.easyInvent.dto.in.BottleDtoIn;
import com.musaRada.easyInvent.enums.BottleStatus;
import com.musaRada.easyInvent.exception.NotFoundException;
import com.musaRada.easyInvent.model.Bottle;
import com.musaRada.easyInvent.repository.BottleRepository;
import com.musaRada.easyInvent.service.interfaces.BottleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor

public class BottleServiceImpl  implements BottleService {
    private  final BottleRepository bottleRepository;
    @Override
    public Bottle addBottle(BottleDtoIn bottleDtoIn) {
        Bottle bottle = new Bottle();
        bottle.setBottleBarcode(bottleDtoIn.getBottleBarcode());
        bottle.setBottleTitle(bottleDtoIn.getBottleTitle());
        bottle.setBottleSize(bottleDtoIn.getBottleSize());
        bottle.setBottleFullWeight(bottle.getBottleFullWeight());
        bottle.setBottleEmptyWeight(bottle.getBottleEmptyWeight());
        return bottleRepository.save(bottle);
    }

    @Override
    public Bottle findBottleByBarcode(String barcode) {
        return bottleRepository.findByBottleBarcodeIs(barcode)
                .orElseThrow(()->new NotFoundException("Бутылка с таким "+barcode+" штрихкодом не найдена!"));
    }

    @Override
    public List<Bottle> findBottleByTitle(String title) {
        return new ArrayList<>(bottleRepository.findAllByBottleTitleContainingAndStatusIs(title, BottleStatus.CONFIRMED.toString()));
    }

    @Override
    public String calculateBottle(Long bottleId, String currentWight) {
      /*
      Бутылка 700 мл
      Целая 1300гр
      Пустая 600гр

      Целая - Пустая = Вес жидкости
      1300 - 600 = 700
      700мл весят 1200гр (условно)
      Взвешиваем бутылку с жидкостью
      1280 гр
      Процент оставшейся жидкости = 1280/1300*100= %
      Недостающий % = 100- %
      Текущее количество жидкости = 700 - недостающий %


       */
        return  null;
    }
}
