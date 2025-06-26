package com.tunelyf.nf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.tunelyf.nf.model.Genre;
import com.tunelyf.nf.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@CrossOrigin(origins = "*")
public class GenreController {

//    @Autowired
//    private GenreService genreService;
//
//    @GetMapping("/all")
//    public List<Genre> getAllGenres() {
//        return genreService.getAllGenres();
//    }
}
