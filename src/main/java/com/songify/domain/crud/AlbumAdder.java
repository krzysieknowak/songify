package com.songify.domain.crud;

import com.songify.domain.crud.dto.AlbumDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
class AlbumAdder {

    private final SongRetriever songRetriever;
    private final AlbumRepository albumRepository;
    AlbumDto addAlbum(Long songId, Instant instant, String title) {
        Song songById = songRetriever.findSongById(songId);
        Album album = new Album();
        album.setTitle(title);
        album.setReleaseDate(instant);
        album.addSong(songById);
        Album saved = albumRepository.save(album);
        return new AlbumDto(saved.getId(), saved.getTitle(),saved.getReleaseDate());
    }
}
