package com.mahd;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class ForexServiceTest {

    @Inject
    ForexService forexService;

    @Test
    void testUsdToUgxConversion() {
        double result = forexService.convert(100, "USD", "UGX");
        assertEquals(380000, result);
    }

    @Test
    void testInvalidAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            forexService.convert(-100, "USD", "UGX");
        });

        assertEquals("Amount must be greater than 0", exception.getMessage());
    }

    @Test
    void testUnsupportedCurrency() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            forexService.convert(100, "USD", "XYZ");
        });

        assertTrue(exception.getMessage().contains("Unsupported currency"));
    }
}