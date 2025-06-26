package com.tunelyf.nf.globalexception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle general exceptions
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAllExceptions(Exception ex, HttpServletRequest request, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("requestUrl", request.getRequestURI());
        return "error";  // refers to templates/error.html
    }

    // Handle file size exceeded
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public String handleMaxSizeException(MaxUploadSizeExceededException ex, Model model) {
        model.addAttribute("errorMessage", "File size exceeds the allowed limit!");
        return "error"; // refers to templates/error.html
    }
}

