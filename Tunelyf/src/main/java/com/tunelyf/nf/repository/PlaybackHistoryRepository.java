package com.tunelyf.nf.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tunelyf.nf.model.PlaybackHistory;

public interface PlaybackHistoryRepository extends JpaRepository<PlaybackHistory, Long> {
    List<PlaybackHistory> findTop20ByUidOrderByPlayedAtDesc(String uid);
}
