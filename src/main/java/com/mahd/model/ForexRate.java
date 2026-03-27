package com.mahd;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "forex_rate")
public class ForexRate extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "from_currency")
    public String fromCurrency;

    @Column(name = "to_currency")
    public String toCurrency;

    public double rate;
}