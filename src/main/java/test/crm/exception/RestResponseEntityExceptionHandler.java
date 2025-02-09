package test.crm.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import test.crm.dto.ResponseDTO;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { EntityNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex,
                                                    WebRequest request) {
        ResponseDTO response = new ResponseDTO();
        response.setHttpCode((short) HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, response,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode statusCode,
                                                                  @NonNull WebRequest request) {
        ResponseDTO response = new ResponseDTO();
        response.setHttpCode((short) HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, response,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        ResponseDTO response = new ResponseDTO();
        response.setHttpCode((short) HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, response,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          @NonNull HttpHeaders headers,
                                                                          @NonNull HttpStatusCode status,
                                                                          @NonNull WebRequest request) {
        ResponseDTO response = new ResponseDTO();
        response.setHttpCode((short) HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, response,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
                                                        @NonNull HttpHeaders headers,
                                                        @NonNull HttpStatusCode status,
                                                        @NonNull WebRequest request) {
        ResponseDTO response = new ResponseDTO();
        response.setHttpCode((short) HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, response,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
