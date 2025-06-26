package com.tunelyf.nf.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tunelyf.nf.exception.SongNotFoundException;

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("currentUri")
    public String currentUri(HttpServletRequest request) {
        return request.getRequestURI();
    }
    
    @ExceptionHandler(SongNotFoundException.class)
    public String handleSongNotFound(SongNotFoundException ex, HttpServletRequest request, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("requestUrl", request.getRequestURI());
        return "error-page"; // maps to error-page.html
    }
}

