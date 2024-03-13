package com.songify.song.domain.service;

import com.songify.song.domain.model.SongEntity;
import com.songify.song.domain.repository.SongRepository;
import org.springframework.stereotype.Service;

@Service
public class SongAdder {
    private final SongRepository songRepository;

    public SongAdder(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public SongEntity addSong(SongEntity song) {
        songRepository.saveToDatabase(song);
        return song;
    }
}
