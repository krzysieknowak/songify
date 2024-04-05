package com.songify.domain.crud.song;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
class SongDeleter {

    private final SongRepository songRepository;
    private final SongRetriever songRetriever;

     SongDeleter(SongRepository songRepository, SongRetriever songRetriever) {
        this.songRepository = songRepository;
        this.songRetriever = songRetriever;
    }

     void deleteSongById(Long id) {
        songRetriever.findSongById(id);
        log.info("Deleting song with id " + id);
        songRepository.deleteById(id);
    }
}
