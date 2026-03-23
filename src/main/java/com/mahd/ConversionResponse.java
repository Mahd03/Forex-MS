package com.mahd;

public class ConversionResponse {
    public double amount;
    public String from;
    public String to;
    public double rate;
    public double result;

    public ConversionResponse(double amount, String from, String to, double rate, double result) {
        this.amount = amount;
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.result = result;
    }
}
