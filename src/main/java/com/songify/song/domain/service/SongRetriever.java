package com.songify.song.domain.service;

import com.songify.song.domain.model.SongEntity;
import com.songify.song.domain.model.SongNotFoundException;
import com.songify.song.domain.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<SongEntity> findSongById(Long id){
        SongEntity song = songRepository.findById(id);
        return Optional.ofNullable(song);
    }
    public void existsById(Long id){
        findSongById(id).orElseThrow(()-> new SongNotFoundException("Song with " + id  + " not found"));
    }
}
