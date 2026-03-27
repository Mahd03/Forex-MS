package com.mahd.repository;

import com.mahd.ForexRate;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ForexRepository implements PanacheRepository<ForexRate> {
    
    //converting currency
    public Optional<ForexRate> findRate(String from, String to) {
        return find("fromCurrency = ?1 and toCurrency = ?2",
                from.toUpperCase(),
                to.toUpperCase())
                .firstResultOptional();
    }
    //fetching rate by id not needed as PanacheRepository provides findByIdOptional method

    //fetching all rates
    public List<ForexRate> getAllRates() {
        return listAll();
    }
}