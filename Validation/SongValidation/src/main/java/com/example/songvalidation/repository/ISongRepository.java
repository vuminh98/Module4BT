package com.example.songvalidation.repository;

import com.example.songvalidation.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISongRepository extends CrudRepository<Song, Long> {
}
