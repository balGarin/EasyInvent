package com.musaRada.easyInvent.model;

import com.musaRada.easyInvent.enums.BottleStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bottles")
public class Bottle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bottle_id")
    private Long id;
    private String bottleTitle;
    private String bottleBarcode;
    private Double bottleFullWeight;
    private Double bottleEmptyWeight;
    private String bottleSize;
    @Enumerated(EnumType.STRING)
    private BottleStatus status = BottleStatus.WAITING;
}
