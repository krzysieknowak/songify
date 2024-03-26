package com.songify.song.domain.service;

import com.songify.song.domain.model.SongEntity;
import com.songify.song.domain.repository.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SongAdder {
    private final SongRepository songRepository;

    public SongAdder(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public SongEntity addSong(SongEntity song) {
        songRepository.save(song);
        return song;
    }
}
