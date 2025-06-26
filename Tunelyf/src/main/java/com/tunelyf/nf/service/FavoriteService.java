// 3. FavoriteService.java
package com.tunelyf.nf.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tunelyf.nf.model.Favorite;
import com.tunelyf.nf.model.Song;
import com.tunelyf.nf.repository.FavoriteRepository;
import com.tunelyf.nf.repository.SongRepository;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private SongRepository songRepository;

    public Favorite addFavorite(String userId, Long songId) {
        if (favoriteRepository.existsByUserIdAndSong_Id(userId, songId)) {
            return null; // already exists
        }
        Optional<Song> songOpt = songRepository.findById(songId);
        if (songOpt.isEmpty()) return null;

        Favorite fav = new Favorite();
        fav.setUserId(userId);
        fav.setSong(songOpt.get());
        return favoriteRepository.save(fav);
    }

    public List<Favorite> getFavorites(String userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public boolean removeFavorite(String userId, Long songId) {
        if (!favoriteRepository.existsByUserIdAndSong_Id(userId, songId)) {
            return false;
        }
        favoriteRepository.deleteByUserIdAndSong_Id(userId, songId);
        return true;
    }
}
