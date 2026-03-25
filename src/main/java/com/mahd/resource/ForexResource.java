package com.mahd.resource;

import com.mahd.ConversionResponse;
import com.mahd.ForexRateRequest;
import com.mahd.service.ForexService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.MediaType;

@Path("/")
public class ForexResource {

    @Inject
    ForexService service;

    @GET
    @Path("/convert")
    @Produces(MediaType.APPLICATION_JSON)
    public ConversionResponse convert(
            @QueryParam("amount") double amount,
            @QueryParam("from") String from,
            @QueryParam("to") String to) {

        double result =
                service.convert(amount, from, to);

        return new ConversionResponse(
                amount,
                from,
                to,
                result / amount,
                result
        );
    }

    @POST
    @Path("/rates")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createRate(
            ForexRateRequest request) {

        service.createRate(
                request.fromCurrency,
                request.toCurrency,
                request.rate
        );

        return "Rate created successfully";
    }
}