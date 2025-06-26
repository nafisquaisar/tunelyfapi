package com.tunelyf.nf.DTO;

public class ArtistDTO {
    private String name;
    private String photoUrl;

    public ArtistDTO(String name, String photoUrl) {
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

	public void setName(String name) {
		this.name = name;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
}
