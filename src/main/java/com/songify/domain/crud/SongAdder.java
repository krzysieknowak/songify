package com.songify.domain.crud;

import com.songify.domain.crud.dto.SongDto;
import com.songify.domain.crud.dto.SongLanguageDto;
import com.songify.domain.crud.dto.SongRequestDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
class SongAdder {
    private final SongRepository songRepository;

     SongDto addSong(SongRequestDto songDto) {
         SongLanguageDto language = songDto.language();
         SongLanguage songLanguage = SongLanguage.valueOf(language.name());
         Song songEntity = Song.builder()
                 .name(songDto.name())
                 .releaseDate(songDto.releaseDate())
                 .duration(songDto.duration())
                 .language(songLanguage)
                 .build();
         Song savedSong = songRepository.save(songEntity);
         return new SongDto(savedSong.getId(),
                 songEntity.getName(),
                 savedSong.getReleaseDate(),
                 savedSong.getDuration());
    }
}
