package com.mahd;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "forex_rate")
public class ForexRate extends PanacheEntity {

    @Column(name = "from_currency")
    public String fromCurrency;

    @Column(name = "to_currency")
    public String toCurrency;

    public double rate;
}