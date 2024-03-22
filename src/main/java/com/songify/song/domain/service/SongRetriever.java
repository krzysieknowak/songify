package com.songify.song.domain.service;

import com.songify.song.domain.model.SongEntity;
import com.songify.song.domain.repository.InMemorySongRepository;
import com.songify.song.domain.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SongRetriever {

    private final SongRepository songRepository;

    public SongRetriever(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<SongEntity> findAll(){
        return songRepository.findAll();
    }
    public List<SongEntity> findAllLimitedBy(Integer limit) {
        List<SongEntity> allSongs = songRepository.findAll();
        return allSongs.stream()
                .limit(limit)
                .toList();
    }
}
