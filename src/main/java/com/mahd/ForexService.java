package com.mahd;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class ForexService {
    public void createRate(String from, String to, double rate) {

        if (from == null || from.isBlank())
            throw new IllegalArgumentException("From currency is required");

        if (to == null || to.isBlank())
            throw new IllegalArgumentException("To currency is required");

        if (rate <= 0)
            throw new IllegalArgumentException("Rate must be greater than 0");

        ForexRate forexRate = new ForexRate();
        forexRate.fromCurrency = from.toUpperCase();
        forexRate.toCurrency = to.toUpperCase();
        forexRate.rate = rate;

        forexRate.persist();
    }

    public double convert(double amount, String from, String to) {
        validate(amount, from, to);

        // Fetch rate from database
        Optional<ForexRate> rateObj = ForexRate.find("fromCurrency = ?1 and toCurrency = ?2",
                from.toUpperCase(), to.toUpperCase())
                .firstResultOptional();

        if (rateObj.isEmpty()) {
            throw new IllegalArgumentException("Unsupported currency conversion: " + from + "_" + to);
        }

        double rate = rateObj.get().rate;
        return amount * rate;
    }

    private void validate(double amount, String from, String to) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than 0");
        if (from == null || from.isBlank())
            throw new IllegalArgumentException("From currency is required");
        if (to == null || to.isBlank())
            throw new IllegalArgumentException("To currency is required");
    }
}