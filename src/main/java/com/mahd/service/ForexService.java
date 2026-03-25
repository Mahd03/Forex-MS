package com.mahd.service;

import com.mahd.ForexRate;
import com.mahd.repository.ForexRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class ForexService {

    @Inject
    ForexRepository repository;

    public double convert(double amount, String from, String to) {

        validate(amount, from, to);

        Optional<ForexRate> rateObj =
                repository.findRate(from, to);

        if (rateObj.isEmpty()) {
            throw new IllegalArgumentException(
                    "Unsupported currency conversion"
            );
        }
        double rate = rateObj.get().rate;
        return amount * rate;
    }

    public void createRate(String from, String to, double rate) {

        ForexRate forexRate = new ForexRate();
        forexRate.fromCurrency = from.toUpperCase();
        forexRate.toCurrency = to.toUpperCase();
        forexRate.rate = rate;

        repository.persist(forexRate);
    }

    private void validate(double amount, String from, String to) {

        if (amount <= 0)
            throw new IllegalArgumentException(
                    "Amount must be greater than 0"
            );

        if (from == null || from.isBlank())
            throw new IllegalArgumentException(
                    "From currency required"
            );

        if (to == null || to.isBlank())
            throw new IllegalArgumentException(
                    "To currency required"
            );
    }
}