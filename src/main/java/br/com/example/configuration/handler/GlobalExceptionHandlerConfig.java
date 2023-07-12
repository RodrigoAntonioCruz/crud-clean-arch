//package br.com.example.configuration.handler;
//
//
//import com.wishlist.exception.BusinessException;
//import com.wishlist.exception.ExceptionResolver;
//import com.wishlist.util.Constants;
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.ConstraintViolationException;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.catalina.connector.ClientAbortException;
//import org.slf4j.MDC;
//import org.springframework.context.support.DefaultMessageSourceResolvable;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.validation.BindException;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static java.util.Optional.ofNullable;
//
//
//@Slf4j
//@ControllerAdvice
//public class GlobalExceptionHandlerConfig {
//
//    @ExceptionHandler(value = {BusinessException.class})
//    protected ResponseEntity<Object> handleConflict(BusinessException ex, WebRequest request) {
//        return getException(ex.getHttpStatusCode(), ex.getMessage(), ex.getDescription(), "BusinessException");
//    }
//
//    @ExceptionHandler({Throwable.class})
//    public ResponseEntity<Object> handleException(Throwable eThrowable) {
//        if (eThrowable.getMessage().contains(Constants.DUPLICATION_CODE)) {
//            if (eThrowable.getMessage().contains(Constants.KEY_CPF)) {
//                return getException(HttpStatus.CONFLICT, HttpStatus.CONFLICT.getReasonPhrase(), Constants.DUPLICATION_CPF, "Throwable");
//            }
//
//            if (eThrowable.getMessage().contains(Constants.KEY_EMAIL)) {
//                return getException(HttpStatus.CONFLICT, HttpStatus.CONFLICT.getReasonPhrase(), Constants.DUPLICATION_EMAIL, "Throwable");
//            }
//
//            if (eThrowable.getMessage().contains(Constants.KEY_TITLE)) {
//                return getException(HttpStatus.CONFLICT, HttpStatus.CONFLICT.getReasonPhrase(), Constants.DUPLICATION_TITLE, "Throwable");
//            }
//
//            if (eThrowable.getMessage().contains(Constants.KEY_DESCRIPTION)) {
//                return getException(HttpStatus.CONFLICT, HttpStatus.CONFLICT.getReasonPhrase(), Constants.DUPLICATION_DESCRIPTION, "Throwable");
//            }
//
//            if (eThrowable.getMessage().contains(Constants.KEY_IMAGE)) {
//                return getException(HttpStatus.CONFLICT, HttpStatus.CONFLICT.getReasonPhrase(), Constants.DUPLICATION_IMAGE, "Throwable");
//            }
//
//        } else {
//            return getException(HttpStatus.INTERNAL_SERVER_ERROR, ofNullable(eThrowable.getMessage()).orElse(eThrowable.toString()), ExceptionResolver.getRootException(eThrowable), "Throwable");
//        }
//        return null;
//    }
//
//    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
//    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exMethod, WebRequest request) {
//        String error = exMethod.getName() + " should be " + Objects.requireNonNull(exMethod.getRequiredType()).getName();
//        return getException(HttpStatus.BAD_REQUEST, Constants.CONSTRAINT_VALIDATION_FAILED, error, "MethodArgumentTypeMismatchException");
//    }
//
//    @ExceptionHandler({ConstraintViolationException.class})
//    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exMethod, WebRequest request) {
//        List<String> errors = new ArrayList<>();
//        for (ConstraintViolation<?> violation : exMethod.getConstraintViolations()) {
//            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
//        }
//
//        return getException(HttpStatus.BAD_REQUEST, Constants.CONSTRAINT_VALIDATION_FAILED, errors.toString(), "ConstraintViolationException");
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> validationError(MethodArgumentNotValidException exMethod) {
//        BindingResult bindingResult = exMethod.getBindingResult();
//        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//        List<String> fieldErrorDtos = fieldErrors.stream().map(f -> f.getField().concat(":").concat(Objects.requireNonNull(f.getDefaultMessage()))).map(String::new).toList();
//
//        return getException(HttpStatus.BAD_REQUEST, Constants.CONSTRAINT_VALIDATION_FAILED, fieldErrorDtos.toString(), "MethodArgumentNotValidException");
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<Object> validationError(HttpMessageNotReadableException exMethod) {
//        return getException(HttpStatus.BAD_REQUEST, Constants.CONSTRAINT_VALIDATION_FAILED, exMethod.getMostSpecificCause().getMessage(), "HttpMessageNotReadableException");
//    }
//
//    @ExceptionHandler({MissingServletRequestParameterException.class})
//    public ResponseEntity<Object> handleException(MissingServletRequestParameterException e) {
//        return getException(HttpStatus.BAD_REQUEST, Optional.of(e.getMessage()).orElse(e.toString()), ExceptionResolver.getRootException(e), "MissingServletRequestParameterException");
//    }
//
//    @ExceptionHandler({EmptyResultDataAccessException.class})
//    public ResponseEntity<Object> handleException(EmptyResultDataAccessException e) {
//        return getException(HttpStatus.NOT_FOUND, Constants.NOT_FOUND, ExceptionResolver.getRootException(e), "EmptyResultDataAccessException");
//    }
//
//    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
//    public ResponseEntity<Object> handleException(HttpRequestMethodNotSupportedException e) {
//        return getException(HttpStatus.METHOD_NOT_ALLOWED, ofNullable(e.getMessage()).orElse(e.toString()), ExceptionResolver.getRootException(e), "HttpRequestMethodNotSupportedException");
//    }
//
//    @ExceptionHandler({ClientAbortException.class})
//    public ResponseEntity<Object> handleException(ClientAbortException e) {
//        return getException(HttpStatus.valueOf(499), ofNullable(e.getMessage()).orElse(e.toString()), ExceptionResolver.getRootException(e), "ClientAbortException");
//    }
//
//    @ExceptionHandler({IOException.class})
//    public ResponseEntity<Object> handleException(IOException e) {
//        return getException(HttpStatus.SERVICE_UNAVAILABLE, ofNullable(e.getMessage()).orElse(e.toString()), null, "IOException");
//    }
//
//    @ExceptionHandler({BindException.class})
//    public ResponseEntity<Object> handleException(BindException e) {
//        String errors = e.getBindingResult().getAllErrors().stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage).filter(Objects::nonNull).map(String::new)
//                .collect(Collectors.joining());
//
//        return getException(HttpStatus.BAD_REQUEST, Constants.CONSTRAINT_VALIDATION_FAILED, errors, "BindException");
//    }
//
//    @ExceptionHandler({NumberFormatException.class})
//    public ResponseEntity<Object> handleException(NumberFormatException e) {
//        return getException(HttpStatus.BAD_REQUEST, ofNullable(e.getMessage()).orElse(e.toString()), ExceptionResolver.getRootException(e), "NumberFormatException");
//    }
//
//    private String getTraceID() {
//        return ofNullable(MDC.get(Constants.TRACE_ID_KEY)).orElse("not available");
//    }
//
//    private ResponseEntity<Object> getException(HttpStatus httpStatus, String message, String description, String method) {
//        BusinessException ex = BusinessException.builder().httpStatusCode(httpStatus).message(message).description(description).build();
//
//        var responseHeaders = new HttpHeaders();
//        responseHeaders.set(Constants.X_RD_TRACEID, getTraceID());
//
//        if (HttpStatus.INTERNAL_SERVER_ERROR.value() == ex.getHttpStatusCode().value()) {
//            log.error(Constants.LOG_KEY_METHOD + Constants.LOG_KEY_EVENT + Constants.LOG_KEY_HTTP_CODE + Constants.LOG_KEY_MESSAGE +
//                    Constants.LOG_KEY_DESCRIPTION, method, Constants.LOG_EXCEPTION, ex.getHttpStatusCode().value(), ex.getMessage(), ex.getDescription(), ex);
//        } else {
//            log.error(Constants.LOG_KEY_METHOD + Constants.LOG_KEY_EVENT + Constants.LOG_KEY_HTTP_CODE + Constants.LOG_KEY_MESSAGE +
//                    Constants.LOG_KEY_DESCRIPTION, method, Constants.LOG_EXCEPTION, ex.getHttpStatusCode().value(), ex.getMessage(), ex.getDescription());
//        }
//
//        return ResponseEntity.status(ex.getHttpStatusCode()).headers(responseHeaders).body(ex.getOnlyBody());
//    }
//}