package com.food.delivery.dto;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class CustomerOrderDto{

    private String phone;
    private Long compilationTime;
    private String postalCode;
    private String description;
    private Double price;
}
