package com.mahd.resource;

import com.mahd.ConversionResponse;
import com.mahd.ForexRateRequest;
import com.mahd.ForexRate;
import com.mahd.service.ForexService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

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

    //fetching all rates
    @GET
    @Path("/rates")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ForexRate> getRates() {
        return service.getAllRates();
    }

    //updating existing rate
    @PUT
    @Path("/rates/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateRate(
            @PathParam("id") Long id,
            ForexRateRequest request) {

        service.updateRate(
                id,
                request.fromCurrency,
                request.toCurrency,
                request.rate
        );

        return "Rate updated successfully";
    }

    //fetching rate by id
    @GET
    @Path("/rates/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ForexRate getRate(@PathParam("id") Long id) {
        return service.getRate(id);
    }

    //deleting a currency rate
    @DELETE
    @Path("/rates/{id}")
    public String deleteRate(@PathParam("id") Long id) {
        service.deleteRate(id);
        return "Rate deleted successfully";
    }

}