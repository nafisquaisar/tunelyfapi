package com.tunelyf.nf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunelyf.nf.model.Playlist;
import com.tunelyf.nf.model.Song;
import com.tunelyf.nf.repository.PlaylistRepository;
import com.tunelyf.nf.repository.SongRepository;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;
    
    @Autowired
    private SongRepository songRepository; 

    // Create a new playlist
    public Playlist createPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    // Get all playlists by UID
    public List<Playlist> getPlaylistsByUid(String uid) {
        return playlistRepository.findByUid(uid);
    }

    // Get a single playlist by ID
    public Optional<Playlist> getPlaylistById(Long id) {
        return playlistRepository.findById(id);
    }

    // Update a playlist
    public Playlist updatePlaylist(Long id, Playlist updatedPlaylist) {
        return playlistRepository.findById(id)
            .map(existing -> {
                existing.setName(updatedPlaylist.getName());
                existing.setDescription(updatedPlaylist.getDescription());
                existing.setCoverUrl(updatedPlaylist.getCoverUrl());
                return playlistRepository.save(existing);
            })
            .orElse(null);
    }

    // Delete a playlist
    public boolean deletePlaylist(Long id) {
        if (playlistRepository.existsById(id)) {
            playlistRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public Playlist addSongToPlaylist(Long playlistId, Long songId) {
        Optional<Playlist> playlistOpt = playlistRepository.findById(playlistId);
        Optional<Song> songOpt = songRepository.findById(songId);

        if (playlistOpt.isPresent() && songOpt.isPresent()) {
            Playlist playlist = playlistOpt.get();
            Song song = songOpt.get();

            if (!playlist.getSongs().contains(song)) {
                playlist.getSongs().add(song);
                return playlistRepository.save(playlist);
            }
        }
        return null;
    }
    
    public Playlist removeSongFromPlaylist(Long playlistId, Long songId) {
        Optional<Playlist> playlistOpt = playlistRepository.findById(playlistId);
        Optional<Song> songOpt = songRepository.findById(songId);

        if (playlistOpt.isPresent() && songOpt.isPresent()) {
            Playlist playlist = playlistOpt.get();
            Song song = songOpt.get();

            if (playlist.getSongs().contains(song)) {
                playlist.getSongs().remove(song);
                return playlistRepository.save(playlist);
            }
        }
        return null;
    }


}
