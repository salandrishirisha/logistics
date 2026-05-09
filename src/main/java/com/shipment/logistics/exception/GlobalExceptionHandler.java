package com.shipment.logistics.exception;

import com.shipment.logistics.response.ApiResponse;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>>
    handleNotFound(
            ResourceNotFoundException ex) {

        ApiResponse<String> response =
                new ApiResponse<>(
                        404,
                        ex.getMessage(),
                        null);

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(
            BadRequestException.class)
    public ResponseEntity<ApiResponse<String>>
    handleBadRequest(
            BadRequestException ex) {

        ApiResponse<String> response =
                new ApiResponse<>(
                        400,
                        ex.getMessage(),
                        null);

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>>
    handleGeneralException(
            Exception ex) {

        ApiResponse<String> response =
                new ApiResponse<>(
                        500,
                        ex.getMessage(),
                        null);

        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}