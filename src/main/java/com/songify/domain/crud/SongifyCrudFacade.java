package com.songify.domain.crud;

import com.songify.domain.crud.dto.AlbumDto;
import com.songify.domain.crud.dto.AlbumRequestDto;
import com.songify.domain.crud.dto.ArtistDto;
import com.songify.domain.crud.dto.ArtistRequestDto;
import com.songify.domain.crud.dto.GenreDto;
import com.songify.domain.crud.dto.GenreRequestDto;
import com.songify.domain.crud.dto.SongDto;
import com.songify.domain.crud.dto.SongRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class SongifyCrudFacade {
    private final SongAdder songAdder;
    private final SongRetriever songRetriever;
    private final SongDeleter songDeleter;
    private final SongUpdater songUpdater;
    private final ArtistAdder artistAdder;
    private final GenreAdder genreAdder;
    private final AlbumAdder albumAdder;
    private final ArtistRetriever artistRetriever;

    public ArtistDto addArtist(ArtistRequestDto request) {
        return artistAdder.addArtist(request.name());
    }

    public AlbumDto addAlbumWithOneSong(AlbumRequestDto request) {
        return albumAdder.addAlbum(request.songId(), request.releaseDate(), request.title());
    }

    public GenreDto addGenre(GenreRequestDto request) {
        return genreAdder.addGenre(request.name());
    }

    public SongDto addSong(final SongRequestDto songRequestDto) {
        return songAdder.addSong(songRequestDto);

    }

    public Set<ArtistDto> findAllArtists(Pageable pageable) {
        return artistRetriever.findAllArtists(pageable);
    }

    public List<SongDto> findAllSongs(final Pageable pageable) {
        return songRetriever.findAll(pageable);
    }

    public SongDto findSongById(final Long id) {
        return songRetriever.findSongDtoById(id);
    }

    public void deleteSongById(final Long id) {
        songDeleter.deleteSongById(id);
    }

    public void updateSongById(final Long id, final SongDto song) {
        Song songEntity = Song.builder()
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
