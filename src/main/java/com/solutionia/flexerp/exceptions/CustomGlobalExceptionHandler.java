package com.solutionia.flexerp.exceptions;

import com.solutionia.flexerp.exceptions.detailsBin.CustomErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Handle method argument not valid exception
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), "Method argument not valid exception", ex.getMessage());

        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }

    // Http request method not supported
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), "Http method not supported!!", ex.getMessage());

        return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    //ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public final  ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Object> handleUserNameNotFoundException(EntityNotFoundException ex,
                                                                        WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
    }


//    @ExceptionHandler(NoResultException.class)
//    public final ResponseEntity<Object> handleNoResultException(NoResultException ex, WebRequest request){
//        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(),
//                request.getDescription(false));
//        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
//    }
}
