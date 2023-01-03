package com.example.uploadsong.service;

import com.example.uploadsong.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();
    void save(Song song);
    Song findById(int id);
    void update(int id, Song song);
    void remove(int id);
}
