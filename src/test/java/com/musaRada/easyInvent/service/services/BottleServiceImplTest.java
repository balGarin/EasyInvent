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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class BottleServiceImplTest {

    private final BottleService bottleService;

    private BottleDtoIn bottleDtoIn;


    @BeforeAll
    void setUp() {
        bottleDtoIn = new BottleDtoIn();
        bottleDtoIn.setBottleBarcode("12345678");
        bottleDtoIn.setBottleSize("1");
        bottleDtoIn.setBottleTitle("Atxa");
        bottleDtoIn.setBottleEmptyWeight("590");
        bottleDtoIn.setBottleFullWeight("1612");

    }

    @Test
    void shouldCorrectAddBottle() {
        BottleDtoOut bottleReturned = bottleService.addBottle(bottleDtoIn);
        assertEquals(bottleDtoIn.getBottleTitle(), bottleReturned.getBottleTitle(), "Имя  не совпадает");
        assertNotNull(bottleReturned.getId(), "Id не появился");
    }


    @Test
    void shouldNotAddBottleWithSameBarcode() {
        BottleDtoOut bottleReturned = bottleService.addBottle(bottleDtoIn);
        assertThrowsExactly(DataIntegrityViolationException.class, () -> bottleService.addBottle(bottleDtoIn), "Удалось добавить бутылки с одинаковым кодом");
    }


    // тест для Репо
//    @Test
//    void  shouldNotAddBottleWithoutAnyOfTheseProperties(){
//        bottleDtoIn.setBottleBarcode(null);
//        assertThrowsExactly(DataIntegrityViolationException.class,()->bottleService.addBottle(bottleDtoIn),"Удалось добавить без кода");
//        bottleDtoIn.setBottleBarcode("12234433");
//        bottleDtoIn.setBottleSize(null);
//        assertThrowsExactly(DataIntegrityViolationException.class,()->bottleService.addBottle(bottleDtoIn),"Удалось добавить без размера");
//        bottleDtoIn.setBottleSize("0,7");
//        bottleDtoIn.setBottleEmptyWeight(null);
//        assertThrowsExactly(DataIntegrityViolationException.class,()->bottleService.addBottle(bottleDtoIn),"Удалось добавить без веса пустой тары");
//        bottleDtoIn.setBottleEmptyWeight("590");
//        bottleDtoIn.setBottleTitle(null);
//        assertThrowsExactly(DataIntegrityViolationException.class,()->bottleService.addBottle(bottleDtoIn),"Удалось добавить без названия");
//
//
//
//
//
//    }


    @Test
    void shouldNotReturnAnythingInPublicSearchMethods() {
        bottleService.addBottle(bottleDtoIn);
        assertThrowsExactly(NotFoundException.class, () -> bottleService.findBottleByBarcode(bottleDtoIn.getBottleBarcode()));
        List<BottleDtoOut> bottles = bottleService.findBottleByTitle(bottleDtoIn.getBottleTitle());
        assertEquals(0, bottles.size(), "Объект видно");
    }


    @Test
    void shouldNotCalculateBottleWhereCurrentWeightLessWeightOfEmptyBottle() {
        BottleDtoOut bottleDtoOut=bottleService.addBottle(bottleDtoIn);
        assertThrowsExactly(IncorrectDataException.class,()->bottleService.calculateBottle(new RequestCalculateBottle(bottleDtoOut.getId(),"450")));
    }


    @Test
    void shouldCorrectSearchBottleByBarcode() {
        BottleDtoOut bottleReturned = bottleService.addBottle(bottleDtoIn);
        bottleService.approveBottle(List.of(bottleReturned.getId()));
        BottleDtoOut bottle = bottleService.findBottleByBarcode(bottleDtoIn.getBottleBarcode());
        assertEquals(bottleDtoIn.getBottleTitle(), bottle.getBottleTitle(), "Имя  не совпадает");

    }

    @Test
    void shouldCorrectSearchBottleByTitle() {
        BottleDtoOut bottleReturned = bottleService.addBottle(bottleDtoIn);
        bottleService.approveBottle(List.of(bottleReturned.getId()));
        List<BottleDtoOut> bottles = bottleService.findBottleByTitle(bottleDtoIn.getBottleTitle());
        assertEquals(bottleDtoIn.getBottleTitle(), bottles.get(0).getBottleTitle(), "Имя  не совпадает");
        bottleDtoIn.setBottleBarcode("12233322");
        bottleReturned = bottleService.addBottle(bottleDtoIn);
        bottleService.approveBottle(List.of(bottleReturned.getId()));
        bottles = bottleService.findBottleByTitle(bottleDtoIn.getBottleTitle());
        assertEquals(2, bottles.size(), "Не верное количество");
    }

    @Test
    void shouldCorrectCalculateBottleWithFullWeight() {
        BottleDtoOut bottleReturned = bottleService.addBottle(bottleDtoIn);
        bottleService.approveBottle(List.of(bottleReturned.getId()));
        String result = bottleService.calculateBottle(new RequestCalculateBottle(bottleReturned.getId(), "0.950"));
        assertEquals("352,25", result, "Расчеты не верны");
    }

    @Test
    void shouldCorrectCalculateBottleWithoutFullWeight() {
        bottleDtoIn.setBottleFullWeight(null);
        BottleDtoOut bottleReturned = bottleService.addBottle(bottleDtoIn);
        bottleService.approveBottle(List.of(bottleReturned.getId()));
        String result = bottleService.calculateBottle(new RequestCalculateBottle(bottleReturned.getId(), "0.950"));
        assertEquals("360", result, "Расчеты не верны");
    }

    @Test
    void shouldCorrectApproveBottle() {
        BottleDtoOut bottleReturned = bottleService.addBottle(bottleDtoIn);
        List<Bottle> bottles = bottleService.getAllBottles(0, 10);
        assertEquals(BottleStatus.WAITING, bottles.get(0).getStatus(), "Статус не верный");
        bottleService.approveBottle(List.of(bottleReturned.getId()));
        bottles = bottleService.getAllBottles(0, 10);
        assertEquals(BottleStatus.CONFIRMED, bottles.get(0).getStatus(), "Статус не верный");
    }

    @Test
    void shouldCorrectSearchAllWaitingBottles() {
        BottleDtoOut bottleReturned1 = bottleService.addBottle(bottleDtoIn);
        bottleDtoIn.setBottleBarcode("12463332");
        BottleDtoOut bottleReturned2 = bottleService.addBottle(bottleDtoIn);
        List<Bottle> bottles = bottleService.getAllWaitingBottles();
        assertEquals(2, bottles.size(), "Количество не верное");
        bottleService.approveBottle(List.of(bottleReturned1.getId()));
        bottles = bottleService.getAllWaitingBottles();
        assertEquals(1, bottles.size(), "Количество не верное");


    }

    @Test
    void shouldCorrectRemoveBottle() {
        BottleDtoOut bottleReturned1 = bottleService.addBottle(bottleDtoIn);
        bottleDtoIn.setBottleBarcode("12463332");
        BottleDtoOut bottleReturned2 = bottleService.addBottle(bottleDtoIn);
        List<Bottle> bottles = bottleService.getAllBottles(0, 10);
        assertEquals(2, bottles.size());
        bottleService.removeBottle(bottleReturned1.getId());
        bottles = bottleService.getAllBottles(0, 10);
        assertEquals(1, bottles.size(), "Бутылка не удалилась");


    }


    @Test
    void shouldCorrectUpdateBottle() {
        BottleDtoOut bottleReturned1 = bottleService.addBottle(bottleDtoIn);
        bottleService.approveBottle(List.of(bottleReturned1.getId()));
        List<Bottle> bottles = bottleService.getAllBottles(0, 10);
        assertEquals(BottleStatus.CONFIRMED, bottles.get(0).getStatus(), "Статус не верный");
        bottleDtoIn.setBottleSize("0.7");
        bottleService.updateBottle(bottleReturned1.getId(), bottleDtoIn);
        bottles = bottleService.getAllBottles(0, 10);
        assertEquals(BottleStatus.WAITING, bottles.get(0).getStatus(), "Статус не верный");
        assertEquals(700.0, bottles.get(0).getBottleSize(), "Статус не верный");

    }


}