package com.musaRada.easyInvent.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BottleUpdateDto {
    private String bottleTitle;
    private String bottleBarcode;
    private Double bottleFullWeight;
    private Double bottleEmptyWeight;
    private String bottleSize;
}
