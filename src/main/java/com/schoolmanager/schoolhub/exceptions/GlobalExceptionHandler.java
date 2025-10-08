package com.schoolmanager.schoolhub.exceptions;

import java.sql.Date;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
    ErrorResponse error = new ErrorResponse(
        new Date(System.currentTimeMillis()),
        HttpStatus.BAD_REQUEST.value(),
        request.getRequestURI(),
        "Runtime Error",
        e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e,
      HttpServletRequest request) {
    ErrorResponse error = new ErrorResponse(
        new Date(System.currentTimeMillis()),
        HttpStatus.NOT_FOUND.value(),
        request.getRequestURI(),
        "Resource not found",
        e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(AlreadyExsitsException.class)
  public ResponseEntity<ErrorResponse> handleAlreadyExistsException(AlreadyExsitsException e,
      HttpServletRequest request) {
    ErrorResponse error = new ErrorResponse(
        new Date(System.currentTimeMillis()),
        HttpStatus.CONFLICT.value(),
        request.getRequestURI(),
        "Alreay Exists",
        e.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  @ExceptionHandler(ImportExcelException.class)
  public ResponseEntity<ErrorResponse> handleImportExcelException(ImportExcelException e, HttpServletRequest request) {
    ErrorResponse error = new ErrorResponse(
        new Date(System.currentTimeMillis()),
        HttpStatus.BAD_REQUEST.value(),
        request.getRequestURI(),
        "Import Error",
        e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e,
      HttpServletRequest request) {
    String message = e.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
        .collect(Collectors.joining(", "));
    ErrorResponse error = new ErrorResponse(
        new Date(System.currentTimeMillis()),
        HttpStatus.BAD_REQUEST.value(),
        request.getRequestURI(),
        "Validation Error",
        message);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(InvalidPasswordException.class)
  public ResponseEntity<ErrorResponse> handleInvalidPasswordException(InvalidPasswordException e,
      HttpServletRequest request) {
    ErrorResponse error = new ErrorResponse(
        new Date(System.currentTimeMillis()),
        HttpStatus.BAD_REQUEST.value(),
        request.getRequestURI(),
        "Invalid Error",
        e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleBadCredentailsException(BadCredentialsException e,
      HttpServletRequest request) {
    ErrorResponse error = new ErrorResponse(
        new Date(System.currentTimeMillis()),
        HttpStatus.UNAUTHORIZED.value(),
        request.getRequestURI(),
        "Unauthorized",
        "Incorrect email or password");
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
  }
}
