package com.musaRada.easyInvent.dto.in;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RequestCalculateBottle {
    @NotNull
    private  final Long bottleId;
    @NotNull
    @Pattern(regexp = "^[0-9]+([\\.,][0-9]+)?",message = "Только цифры")
    private final String currentWeight;
}
