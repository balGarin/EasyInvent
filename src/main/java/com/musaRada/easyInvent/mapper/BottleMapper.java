package com.musaRada.easyInvent.mapper;

import com.musaRada.easyInvent.dto.in.BottleDtoIn;
import com.musaRada.easyInvent.dto.out.BottleDtoOut;
import com.musaRada.easyInvent.model.Bottle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BottleMapper {

    @Mapping(target = "bottleEmptyWeight",
            expression = "java(bottleDtoIn.getBottleEmptyWeight().length()==1||bottleDtoIn.getBottleEmptyWeight().charAt(1)==','||bottleDtoIn.getBottleEmptyWeight().charAt(1)=='.'?Double\n" +
                    "            .parseDouble(bottleDtoIn.getBottleEmptyWeight().replace(\",\",\".\"))*1000:Double\n" +
                    "            .parseDouble(bottleDtoIn.getBottleEmptyWeight().replace(\",\",\".\")))")
    @Mapping(target = "bottleSize",
            expression = "java(bottleDtoIn.getBottleSize().startsWith(\"0\")||bottleDtoIn.getBottleSize().length()==1||\n" +
                    "bottleDtoIn.getBottleSize().charAt(1)==','||bottleDtoIn.getBottleSize().charAt(1)=='.'?Double\n"+
                    "            .parseDouble(bottleDtoIn.getBottleSize().replace(\",\",\".\"))*1000:Double\n" +
                    "            .parseDouble(bottleDtoIn.getBottleSize().replace(\",\",\".\")))")
    @Mapping(target = "bottleFullWeight",
            expression = "java(bottleDtoIn.getBottleFullWeight()==null?null:bottleDtoIn.getBottleFullWeight().length()==1||\n" +
                    "bottleDtoIn.getBottleFullWeight().charAt(1)==','||bottleDtoIn.getBottleFullWeight().charAt(1)=='.'?Double\n" +
                    "            .parseDouble(bottleDtoIn.getBottleFullWeight().replace(\",\",\".\"))*1000:Double\n" +
                    "            .parseDouble(bottleDtoIn.getBottleFullWeight().replace(\",\",\".\")))"
    )
    Bottle toBottle(BottleDtoIn bottleDtoIn);

    BottleDtoOut toDto(Bottle bottle);

    List<BottleDtoOut> toDto(List<Bottle> bottles);


}
