package com.example.uploadsong.service;

import com.example.uploadsong.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SongService implements ISongService{

private final List<Song> songList = new ArrayList<>();
    @Override
    public List<Song> findAll() {
        return songList;
    }

    @Override
    public void save(Song song) {
        song.setId(songList.size());
        songList.add(song);
    }

    @Override
    public Song findById(int id) {
        return songList.get(id);
    }

    @Override
    public void update(int id, Song song) {
        songList.set(id,song);
    }

    @Override
    public void remove(int id) {
        songList.remove(id);
    }
}
