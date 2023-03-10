package com.example.songvalidation.model;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SongForm {
    private Long id;
    @NotBlank
    @Pattern(regexp = "^[a-zA-z0-9 ]+$", message = "cannot contain special character")
    private String name;
    @NotBlank
    @Pattern(regexp = "^[a-zA-z0-9 ]+$", message = "cannot contain special character")
    private String artist;
    @NotBlank
    @Pattern(regexp = "^[a-zA-z0-9 ]+$", message = "cannot contain special character")
    private String category;
    private MultipartFile audio;

    public SongForm() {
    }

    public SongForm(Long id, String name, String artist, String category, MultipartFile audio) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.category = category;
        this.audio = audio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public MultipartFile getAudio() {
        return audio;
    }

    public void setAudio(MultipartFile audio) {
        this.audio = audio;
    }
}
