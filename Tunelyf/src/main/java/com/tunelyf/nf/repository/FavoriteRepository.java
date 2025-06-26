
// 2. FavoriteRepository.java
package com.tunelyf.nf.repository;

import org.springframework.data.domain.Pageable; // ✅ CORRECT
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tunelyf.nf.model.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(String userId);
    boolean existsByUserIdAndSong_Id(String userId, Long songId);
    void deleteByUserIdAndSong_Id(String userId, Long songId);
    @Query("SELECT f.song.id FROM Favorite f GROUP BY f.song.id ORDER BY COUNT(f.song.id) DESC")
    List<Long> findTopTrendingSongIds(org.springframework.data.domain.Pageable pageable);
}