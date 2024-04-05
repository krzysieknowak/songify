package com.songify.domain.crud;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class SongRetriever {

    private final SongRepository songRepository;

     SongRetriever(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

     List<SongEntity> findAll(Pageable pageable){
        return songRepository.findAll(pageable);
    }
     SongEntity findSongById(Long id){
        return songRepository.findById(id).
                orElseThrow(()-> new SongNotFoundException("Song with " + id  + " not found"));

    }
}
