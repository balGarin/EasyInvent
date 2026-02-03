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
    @Column(name ="bottle_title")
    private String bottleTitle;
    @Column(name ="bottle_barcode")
    private String bottleBarcode;
    @Column(name = "bottle_full_weight")
    private Double bottleFullWeight;
    @Column(name = "bottle_empty_weight")
    private Double bottleEmptyWeight;
    @Column(name = "bottle_size")
    private Double bottleSize;
    @Enumerated(EnumType.STRING)
    private BottleStatus status = BottleStatus.WAITING;
}
