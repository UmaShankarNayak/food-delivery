package com.food.delivery.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "DELIVERY_AGENT_TBL")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DeliveryBoy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "phoneNo")
    private String phoneNo;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private boolean isAvailable;

    @Column(name = "availableTime")
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime availableTime;
}
