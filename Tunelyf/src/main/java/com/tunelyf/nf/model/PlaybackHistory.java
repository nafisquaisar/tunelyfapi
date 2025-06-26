package com.tunelyf.nf.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class PlaybackHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uid;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    @Temporal(TemporalType.TIMESTAMP)
    private Date playedAt = new Date();

    // Getters & Setters
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public Date getPlayedAt() {
		return playedAt;
	}

	public void setPlayedAt(Date playedAt) {
		this.playedAt = playedAt;
	}

	
}
