package io.bootify.expensify.model;

import java.time.LocalDate;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


public class ExpenseDTO {

    private Long id;

    @Size(max = 255)
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Size(max = 255)
    private String account;

    private Double hotel;

    private Double transport;

    private Double fuel;

    private Double meals;

    private Double phone;

    private Double entertainment;

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
