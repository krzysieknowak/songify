package com.songify.song.domain.service;

import com.songify.song.domain.model.SongEntity;
import com.songify.song.domain.model.SongNotFoundException;
import com.songify.song.domain.repository.SongRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongRetriever {

    private final SongRepository songRepository;

    public SongRetriever(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<SongEntity> findAll(Pageable pageable){
        return songRepository.findAll(pageable);
    }
    public SongEntity findSongById(Long id){
        return songRepository.findById(id).
                orElseThrow(()-> new SongNotFoundException("Song with " + id  + " not found"));

    }
}
