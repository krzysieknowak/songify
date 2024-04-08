package com.songify.domain.crud;

import com.songify.domain.crud.dto.ArtistDto;
import com.songify.domain.crud.dto.ArtistRequestDto;
import com.songify.domain.crud.dto.SongEntityDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SongifyCrudFacade {
    private final SongAdder songAdder;
    private final SongRetriever songRetriever;
    private final SongDeleter songDeleter;
    private final SongUpdater songUpdater;
    private final ArtistAdder artistAdder;

    public ArtistDto addArtist(ArtistRequestDto request){
        return artistAdder.addArtist(request.name());

    }

    public List<SongEntityDto> findAll(final Pageable pageable) {
        return songRetriever.findAll(pageable)
                .stream()
                .map(songEntity -> SongEntityDto.builder()
                        .id(songEntity.getId())
                        .name(songEntity.getName())
                        .build())
                .toList();
    }

    public SongEntityDto findSongById(final Long id) {
        SongEntity song = songRetriever.findSongById(id);
        SongEntityDto songEntityDto = SongEntityDto.builder()
                .id(song.getId())
                .name(song.getName())
                .build();
        return songEntityDto;
    }

    public SongEntityDto addSong(final SongEntityDto song) {
        SongEntity songEntity = songAdder.addSong(song);
        return SongEntityDto.builder()
                .name(songEntity.getName())
                .build();
    }

    public void deleteSongById(final Long id) {
        songDeleter.deleteSongById(id);
    }

    public void updateSongById(final Long id, final SongEntityDto song) {
        SongEntity songEntity = SongEntity.builder()
                .name(song.name())
                .build();
        songUpdater.updateSongById(id, songEntity);
    }
}

//    public void updateSongPartiallyById(final Long id, final SongEntityDto song) {
//        SongEntity songEntityToUpdate = SongEntity.builder()
//                .name(song.name())
//
//                .build();
//        songUpdater.updateSongPartiallyById(id, songEntityToUpdate);
//    }
//}
