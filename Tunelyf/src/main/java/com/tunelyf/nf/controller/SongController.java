package com.tunelyf.nf.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tunelyf.nf.model.Song;
import com.tunelyf.nf.service.SongService;



@RestController
@RequestMapping("/api/songs")
@CrossOrigin(origins = "*") // for Android access
public class SongController {

    
    @Autowired
    private SongService songService;

    @GetMapping
    public List<Song> getAllVisibleSongs() {
        return songService.getAllVisibleSongs();
    }
    
    @GetMapping("/allsong")
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/artist/{name}")
    public List<Song> getSongsByArtist(@PathVariable("name") String name) {
        return songService.getSongsByArtist(name);
    }


 // Backend API (Controller in backend Spring Boot app)
    @GetMapping("/genre/{genre}")
    public List<Song> getSongsByGenre(@PathVariable("genre") String genre) {
        return songService.getSongsByGenre(genre);
    }



    @PostMapping("/upload")
    public ResponseEntity<Song> uploadSong(
        @RequestParam("title") String title,
        @RequestParam("artist") String artist,
        @RequestParam("genre") String genre,
        @RequestParam("thumbnailFile") MultipartFile thumbnailFile,
        @RequestParam("songFile") MultipartFile songFile
    ) {
        Song song = songService.uploadSong(title, artist, genre, thumbnailFile, songFile);

        return ResponseEntity.ok(song);
    }

    @PostMapping("/edit")
    public ResponseEntity<Song> editSong(
            @RequestParam("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("artist") String artist,
            @RequestParam("genre") String genre,
            @RequestParam(value = "thumbnailFile", required = false) MultipartFile thumbnailFile,
            @RequestParam(value = "songFile", required = false) MultipartFile songFile) {

        Song updated = songService.updateSong(id, title, artist, genre, thumbnailFile, songFile);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable("id") Long id) {
        songService.deleteSong(id);
    }

    @PatchMapping("/{id}/hide")
    public Song toggleHide(@PathVariable("id") Long id) {
        return songService.toggleHideSong(id);
    }
    
    // 🔍 Search songs by title or artist
    @GetMapping("/search")
    public ResponseEntity<List<Song>> searchSongs(@RequestParam String query) {
        return ResponseEntity.ok(songService.searchSongs(query));
    }

    // 🔥 Get trending songs based on most favorited
    @GetMapping("/trending")
    public ResponseEntity<List<Song>> getTrendingSongs() {
        return ResponseEntity.ok(songService.getTrendingSongs());
    }

    // 🎧 Save playback (user played a song)
    @PostMapping("/played/{songId}")
    public ResponseEntity<?> recordPlay(@PathVariable Long songId, @RequestParam String uid) {
        songService.recordPlayback(uid, songId);
        return ResponseEntity.ok("Playback recorded.");
    }

    // Get recently played songs
    @GetMapping("/recently-played/{uid}")
    public ResponseEntity<List<Song>> getRecentlyPlayed(@PathVariable String uid) {
        return ResponseEntity.ok(songService.getRecentlyPlayedSongs(uid));
    }

}
