package com.mahd;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Consumes;

@Path("/")
public class ForexResource {

    @Inject
    ForexService forexService;

    @GET
    @Path("/convert")
    @Produces(MediaType.APPLICATION_JSON)
    public ConversionResponse convert(
            @QueryParam("amount") double amount,
            @QueryParam("from") String from,
            @QueryParam("to") String to) {

        double result = forexService.convert(amount, from, to);

        return new ConversionResponse(amount, from, to, result / amount, result);
    }

    @POST
    @Path("/rates")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createRate(ForexRateRequest request) {
        forexService.createRate(
            request.fromCurrency,
            request.toCurrency,
            request.rate
        );
        return "Rate created successfully";
    }
}