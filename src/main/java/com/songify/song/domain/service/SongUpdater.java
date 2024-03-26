package com.songify.song.domain.service;

import com.songify.song.domain.model.SongEntity;
import com.songify.song.domain.repository.SongRepository;
import com.songify.song.infrastructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
public class SongUpdater {

    private final SongRepository songRepository;
    private final SongRetriever songRetriever;

    public SongUpdater(SongRepository songRepository, SongRetriever songRetriever) {
        this.songRepository = songRepository;
        this.songRetriever = songRetriever;
    }


    public void updateSongById(Long id, SongEntity newSong) {
        songRepository.findById(id);
        songRepository.updateById(id, newSong);
    }

    public SongEntity updateSongPartiallyById(Long id, SongEntity requestSong) {
        SongEntity songFromDatabase = songRetriever.findSongById(id);
        SongEntity.SongEntityBuilder builder = SongEntity.builder();
        if(requestSong.getName() != null) {
            builder.name(requestSong.getName());
        }else {
            builder.name(songFromDatabase.getName());
        }
        if(requestSong.getArtist() != null){
            builder.artist(requestSong.getArtist());
        }else
            builder.artist(songFromDatabase.getArtist());
        SongEntity toSaveSong = builder.build();
        updateSongById(id,toSaveSong);
        return toSaveSong;
    }
//    public SongEntity updateSongPartiallyById(Long id, SongEntity requestSong) {
//        SongEntity toUpdateSong = songRetriever.findSongById(id);
//        if(requestSong.getName() != null) {
//            toUpdateSong.setName(requestSong.getName());
//        }
//        if(requestSong.getArtist() != null){
//            toUpdateSong.setArtist(requestSong.getArtist());
//        }
//        return toUpdateSong;
//    }
}
