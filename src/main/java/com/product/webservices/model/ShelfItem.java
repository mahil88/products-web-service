package com.product.webservices.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class ShelfItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Double relevancyScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopper_personalized_info_id")
    private Shopper shopper;
}
