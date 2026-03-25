package com.mahd;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<IllegalArgumentException> {

    @Override
    public Response toResponse(IllegalArgumentException exception) {

        ErrorResponse error = new ErrorResponse(exception.getMessage());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .build();
    }
}