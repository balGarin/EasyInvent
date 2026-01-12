package com.musaRada.easyInvent.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bottle")
public class Bottle {
    private Long id;
    private String bottleTitle;
    private String bottleBarcode;
    private Integer bottleFullWeight;
    private Integer bottleEmptyWeight;
    private String bottleSize;
}
