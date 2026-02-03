package com.musaRada.easyInvent.service.services;

import com.musaRada.easyInvent.dto.in.BottleDtoIn;
import com.musaRada.easyInvent.dto.in.RequestCalculateBottle;
import com.musaRada.easyInvent.dto.out.BottleDtoOut;
import com.musaRada.easyInvent.enums.BottleStatus;
import com.musaRada.easyInvent.exception.IncorrectDataException;
import com.musaRada.easyInvent.exception.NotFoundException;
import com.musaRada.easyInvent.mapper.BottleMapper;
import com.musaRada.easyInvent.model.Bottle;
import com.musaRada.easyInvent.repository.BottleRepository;
import com.musaRada.easyInvent.service.interfaces.BottleService;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class BottleServiceImpl implements BottleService {
    private final BottleRepository bottleRepository;
    private final BottleMapper bottleMapper;

    @Override
    public BottleDtoOut addBottle(BottleDtoIn bottleDtoIn) {
        Bottle bottle = bottleMapper.toBottle(bottleDtoIn);
        bottle = bottleRepository.save(bottle);
        return bottleMapper.toDto(bottle);
    }

    @Override
    public BottleDtoOut findBottleByBarcode(String barcode) {
        Bottle bottle = bottleRepository.findByBottleBarcodeIsAndStatusIs(barcode, BottleStatus.CONFIRMED)
                .orElseThrow(() -> new NotFoundException("Бутылка с таким " + barcode + " штрихкодом не найдена!"));
        return bottleMapper.toDto(bottle);
    }

    @Override
    public List<BottleDtoOut> findBottleByTitle(String title) {
        List<Bottle> bottles = new ArrayList<>(bottleRepository
                .findAllByBottleTitleIgnoreCaseContainingAndStatusIs(title, BottleStatus.CONFIRMED));
        return bottleMapper.toDto(bottles);
    }

    @Override
    public String calculateBottle(RequestCalculateBottle requestCalculateBottle) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Bottle bottle = bottleRepository.findById(requestCalculateBottle.getBottleId())
                .orElseThrow(() -> new NotFoundException("Бутылка с ID " + requestCalculateBottle.getBottleId() + " не найдена"));
        String currentWight = requestCalculateBottle.getCurrentWeight();


        double wight = currentWight.length() == 1 || currentWight.charAt(1) == ',' || currentWight.charAt(1) == '.' ? Double.parseDouble(currentWight.replace(",", ".")) * 1000
                : Double.parseDouble(currentWight.replace(",", "."));

        if (bottle.getBottleEmptyWeight() > wight) {
            throw new IncorrectDataException("Некорректный ввод");
        }
        if (bottle.getBottleFullWeight() == null) {
            double result = wight - bottle.getBottleEmptyWeight();
            return decimalFormat.format(result);
        } else {
            double weightOfFullLiquid = bottle.getBottleFullWeight() - bottle.getBottleEmptyWeight();
            double wightOfCurrentLiquid = wight - bottle.getBottleEmptyWeight();
            double percent = (wightOfCurrentLiquid / weightOfFullLiquid * 100);
            Double result = bottle.getBottleSize() * (percent / 100);
            return decimalFormat.format(result);
        }
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
    }

    @Override
    public String approveBottle(List<Long> bottlesIds) {
        List<Bottle> bottles = bottleRepository.findAllByStatusIsAndIdIn(BottleStatus.WAITING, bottlesIds);
        bottles.stream()
                .peek(bottle -> bottle.setStatus(BottleStatus.CONFIRMED))
                .collect(Collectors.toList());
        bottleRepository.saveAll(bottles);
        return "Бутылки  успешно подтверждены!";
    }

    @Override
    @Description(value = "For Admin")
    public List<Bottle> getAllWaitingBottles() {
        return bottleRepository.findAllByStatusIs(BottleStatus.WAITING);

    }

    @Override
    @Description(value = "For Admin")
    public String removeBottle(Long bottleId) {
        Bottle bottle = bottleRepository.findById(bottleId)
                .orElseThrow(() -> new NotFoundException("Бутылка с таким " + bottleId + " id не найдена!"));
        bottleRepository.delete(bottle);
        return "Бутылка " + bottle.getBottleTitle() + " успешно удалена!";

    }

    @Override
    @Description(value = "For Admin")
    public List<Bottle> getAllBottles(Integer from, Integer limit) {
        PageRequest pageRequest = PageRequest.of(from, limit, Sort.by("bottleTitle"));
        Page<Bottle> page = bottleRepository.findAll(pageRequest);
        return page.stream().toList();
    }

    @Override
    public BottleDtoOut updateBottle(Long bottleId, BottleDtoIn bottleDtoIn) {
        Bottle entityBottle = bottleRepository.findById(bottleId)
                .orElseThrow(() -> new NotFoundException("Бутылка с таким " + bottleId + " id не найдена!"));
        Bottle bottleForChange = bottleMapper.toBottle(bottleDtoIn);
        entityBottle.setBottleSize(bottleForChange.getBottleSize());
        entityBottle.setBottleTitle(bottleForChange.getBottleTitle());
        entityBottle.setBottleBarcode(bottleForChange.getBottleBarcode());
        entityBottle.setBottleEmptyWeight(bottleForChange.getBottleEmptyWeight());
        entityBottle.setBottleFullWeight(bottleForChange.getBottleFullWeight());
        entityBottle.setStatus(BottleStatus.WAITING);
        entityBottle = bottleRepository.save(entityBottle);
        return bottleMapper.toDto(entityBottle);
    }


}
