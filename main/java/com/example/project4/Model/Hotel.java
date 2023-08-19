package com.example.project4.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name should be not empty")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String apartment;

    @NotNull(message = "floor number should be not empty")
    private Integer floorNumber;

    @Positive
    @Column(columnDefinition = "int not null check( rooms > 0 )")
    private Integer rooms;

    @Positive(message = "price must be positive")
    @Column(columnDefinition = "int not null check( price >= 0 )")
    private Integer price;


    @Column(columnDefinition = "boolean default 0")
    private Boolean isBooked;
    // is isBooked is true then by who ? this byUsername well tell

    @Column(columnDefinition = "varchar(20) default ''")
    private String byUsername;


    @Column(columnDefinition = "int default 0")
    private Integer discount;

}
