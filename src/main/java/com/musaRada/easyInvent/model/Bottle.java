package com.musaRada.easyInvent.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bottle")
public class Bottle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bottle_id")
    private Long id;
    private String bottleTitle;
    private String bottleBarcode;
    private Integer bottleFullWeight;
    private Integer bottleEmptyWeight;
    private String bottleSize;
}
