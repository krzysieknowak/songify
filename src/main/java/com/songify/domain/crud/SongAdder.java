package com.songify.domain.crud;

import com.songify.domain.crud.dto.SongEntityDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
class SongAdder {
    private final SongRepository songRepository;

     SongEntity addSong(SongEntityDto song) {
         SongEntity songEntity = SongEntity.builder()
                 .name(song.name())
                 .build();
         songRepository.save(songEntity);
         return songEntity;
    }
}
