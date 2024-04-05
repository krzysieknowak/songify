package com.songify.domain.crud.song;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
class SongUpdater {

    private final SongRepository songRepository;
    private final SongRetriever songRetriever;

     SongUpdater(SongRepository songRepository, SongRetriever songRetriever) {
        this.songRepository = songRepository;
        this.songRetriever = songRetriever;
    }


     void updateSongById(Long id, SongEntity newSong) {
        songRepository.findById(id);
        songRepository.updateById(id, newSong);
    }

     SongEntity updateSongPartiallyById(Long id, SongEntity requestSong) {
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
//     SongEntity updateSongPartiallyById(Long id, SongEntity requestSong) {
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
