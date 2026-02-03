package com.musaRada.easyInvent.dto.in;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BottleDtoIn {
    @NotBlank(message = "Поле не может быть пустым!")
    @NotNull(message = "Поле не может быть пустым!")
    private String bottleTitle;
    @NotBlank(message = "Поле не может быть пустым!")
    @NotNull(message = "Поле не может быть пустым!")
    @Pattern(regexp = "^[0-9]+?",message = "Только цифры")
    private String bottleBarcode;
    @Pattern(regexp = "^[0-9]+([\\.,][0-9]+)?",message = "Только цифры")
    private String bottleFullWeight;
    @NotNull(message = "Поле не может быть пустым!")
    @Pattern(regexp = "^[0-9]+([\\.,][0-9]+)?",message = "Только цифры")
    private String bottleEmptyWeight;
    @NotNull(message = "Поле не может быть пустым!")
    @Pattern(regexp = "^[0-9]+([\\.,][0-9]+)?",message = "Только цифры")
    private String bottleSize ;
}
