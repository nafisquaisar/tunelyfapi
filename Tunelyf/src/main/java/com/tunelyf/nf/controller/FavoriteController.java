// 4. FavoriteController.java
package com.tunelyf.nf.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tunelyf.nf.model.Favorite;
import com.tunelyf.nf.service.FavoriteService;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "*")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<?> addFavorite(@RequestParam String userId, @RequestParam Long songId) {
        Favorite fav = favoriteService.addFavorite(userId, songId);
        return fav != null ? ResponseEntity.ok(fav) : ResponseEntity.badRequest().body("Already favorited or invalid song.");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Favorite>> getFavorites(@PathVariable String userId) {
        return ResponseEntity.ok(favoriteService.getFavorites(userId));
    }

    @DeleteMapping("/{userId}/{songId}")
    public ResponseEntity<?> removeFavorite(@PathVariable String userId, @PathVariable Long songId) {
        boolean removed = favoriteService.removeFavorite(userId, songId);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
