package io.bootify.expensify.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
public class Expense {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column
    private String description;

    @Column
    private LocalDate date;

    @Column
    private String account;

    @Column
    private Double hotel;

    @Column
    private Double transport;

    @Column
    private Double fuel;

    @Column
    private Double meals;

    @Column
    private Double phone;

    @Column
    private Double entertainment;

    @Column
    private Double misc;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(final String account) {
        this.account = account;
    }

    public Double getHotel() {
        return hotel;
    }

    public void setHotel(final Double hotel) {
        this.hotel = hotel;
    }

    public Double getTransport() {
        return transport;
    }

    public void setTransport(final Double transport) {
        this.transport = transport;
    }

    public Double getFuel() {
        return fuel;
    }

    public void setFuel(final Double fuel) {
        this.fuel = fuel;
    }

    public Double getMeals() {
        return meals;
    }

    public void setMeals(final Double meals) {
        this.meals = meals;
    }

    public Double getPhone() {
        return phone;
    }

    public void setPhone(final Double phone) {
        this.phone = phone;
    }

    public Double getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(final Double entertainment) {
        this.entertainment = entertainment;
    }

    public Double getMisc() {
        return misc;
    }

    public void setMisc(final Double misc) {
        this.misc = misc;
    }

}
