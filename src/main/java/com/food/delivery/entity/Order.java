package com.food.delivery.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;


@Entity
@Table(name = "ORDER_TBL")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "delivery_price")
    private Double price;

    @Column(name = "status")
    private String status;

    @Column(name = "creationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime creationDate;

    @Column(name = "compilationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime compilationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_phoneNo", referencedColumnName = "phoneNo")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_agent_phoneNo", referencedColumnName = "phoneNo")
    private DeliveryBoy deliveryBoy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}
