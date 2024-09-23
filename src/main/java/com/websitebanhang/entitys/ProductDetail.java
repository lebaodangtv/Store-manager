package com.websitebanhang.entitys;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productdetail")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    String name;

    @Column
    String description;

    @OneToOne
    @JoinColumn(name = "product_id")
    Products products;
}
