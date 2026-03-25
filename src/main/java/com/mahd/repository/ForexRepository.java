package com.mahd.repository;

import com.mahd.ForexRate;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ForexRepository implements PanacheRepository<ForexRate> {

    public Optional<ForexRate> findRate(String from, String to) {
        return find("fromCurrency = ?1 and toCurrency = ?2",
                from.toUpperCase(),
                to.toUpperCase())
                .firstResultOptional();
    }

    //fetching all rates
    public List<ForexRate> getAllRates() {
        return listAll();
    }
}