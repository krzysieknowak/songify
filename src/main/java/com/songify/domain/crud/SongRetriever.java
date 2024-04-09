package com.songify.domain.crud;

import com.songify.domain.crud.dto.SongDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
class SongRetriever {

    private final SongRepository songRepository;

     SongRetriever(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

     List<SongDto> findAll(Pageable pageable){
         return songRepository.findAll(pageable)
                 .stream()
                 .map(songEntity -> SongDto.builder()
                         .id(songEntity.getId())
                         .name(songEntity.getName())
                         .duration(songEntity.getDuration())
                         .releaseDate(songEntity.getReleaseDate())
                         .build())
                 .toList();

     }
     SongDto findSongDtoById(Long id){
         return songRepository.findById(id)
                 .map(song -> SongDto.builder()
                         .id(song.getId())
                         .name(song.getName())
                         .duration(song.getDuration())
                         .releaseDate(song.getReleaseDate())
                         .build()).
                 orElseThrow(() -> new SongNotFoundException("Song with " + id + " not found"));
     }
    Song findSongById(Long id){
        return songRepository.findById(id).
                orElseThrow(() -> new SongNotFoundException("Song with " + id + " not found"));
    }
}
