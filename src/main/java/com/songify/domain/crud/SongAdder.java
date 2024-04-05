package com.songify.domain.crud;

import com.songify.domain.crud.dto.SongEntityDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
class SongAdder {
    private final SongRepository songRepository;

     SongAdder(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

     SongEntity addSong(SongEntityDto song) {
         SongEntity songEntity = SongEntity.builder()
                 .name(song.name())
                 .artist(song.artist())
                 .build();
         songRepository.save(songEntity);
         return songEntity;
    }
}
