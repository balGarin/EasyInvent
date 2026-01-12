package com.musaRada.easyInvent.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BottleDtoIn {
    @NotBlank
    @NotNull
    private String bottleTitle;
    @NotBlank
    @NotNull
    private String bottleBarcode;
    @NotBlank
    @NotNull
    private Integer bottleFullWeight;
    @NotBlank
    @NotNull
    private Integer bottleEmptyWeight;
    @NotBlank
    @NotNull
    private String bottleSize;
}
