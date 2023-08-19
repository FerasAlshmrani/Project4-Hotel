package com.example.project4.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name should not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "username should not be empty")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    @NotEmpty(message = "varchar(20) not null")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty(message = "phone should be not empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String phone;

    @Email
    @NotEmpty(message = "email should be not empty")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    @PositiveOrZero(message = "balance must be positive")
    @NotNull(message = "balance should be not empty")
    @Column(columnDefinition = "int default 0 ")
    private Integer balance;

    // in the postman these 3 var should be null until i use buy apartment

    @Column(columnDefinition = "boolean default 0")
    private Boolean isBooked;

    // if he booked by which room he got?
    @Column(columnDefinition = "varchar(20) default ''")
    private String whichApartment;

    @PositiveOrZero(message = "duration must be positive")
    @Column(columnDefinition = "int default 0")
    private Integer duration;


}
