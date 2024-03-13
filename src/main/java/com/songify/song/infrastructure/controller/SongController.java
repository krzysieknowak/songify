package com.songify.song.infrastructure.controller;

import com.songify.song.domain.service.SongAdder;
import com.songify.song.domain.service.SongRetriever;
import com.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.response.DeleteSongResponseDto;
import com.songify.song.infrastructure.controller.dto.response.SongResponseDto;
import com.songify.song.infrastructure.controller.dto.response.SongsResponseDto;
import com.songify.song.infrastructure.controller.dto.response.UpdateSongResponseDto;
import com.songify.song.infrastructure.controller.dto.request.SongRequestDto;
import com.songify.song.domain.model.SongNotFoundException;
import com.songify.song.domain.model.SongEntity;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

import static com.songify.song.domain.service.SongMapper.mapFromSongRequestDtoToSongEntity;
import static com.songify.song.domain.service.SongMapper.mapFromSongToSongResponseDto;

@RestController
@Log4j2
@RequestMapping("/songs")
public class SongController {

    private final SongAdder songAdder;
    private final SongRetriever songRetriever;


    public SongController(SongAdder songAdder, SongRetriever songRetriever) {
        this.songAdder = songAdder;
        this.songRetriever = songRetriever;
    }


    @GetMapping
    public ResponseEntity <SongsResponseDto> getSongs(@RequestParam(required = false) Integer limit){
        Map<Integer, SongEntity> allSongs = songRetriever.findAll();
        if(limit != null){
            Map<Integer, SongEntity> limitedMap = songRetriever.findAllLimitedBy(limit);
            SongsResponseDto responseDto = new SongsResponseDto(limitedMap);
            return ResponseEntity.ok(responseDto);
        }
        SongsResponseDto responseDto = new SongsResponseDto(allSongs);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity <SongResponseDto> getSongById(@PathVariable Integer id, @RequestHeader(required = false) String requestId){
        log.info("Header: " + requestId);
        Map<Integer, SongEntity> allSongs = songRetriever.findAll();
        if(!allSongs.containsKey(id)){
            throw new SongNotFoundException("Song not found");
        }
        SongEntity song = allSongs.get(id);
        if(song == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(new SongResponseDto(song));
    }

    @PostMapping
    public ResponseEntity <SongResponseDto> postSong(@RequestBody @Valid SongRequestDto request){
        SongEntity song = mapFromSongRequestDtoToSongEntity(request);
        songAdder.addSong(song);
        SongResponseDto response = mapFromSongToSongResponseDto(song);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSongResponseDto>deleteSongById(@PathVariable @Valid Integer id){
        Map<Integer, SongEntity> allSongs = songRetriever.findAll();
        if(!allSongs.containsKey(id)){
            throw new SongNotFoundException("Song not found");
        }
        SongEntity removedSong = allSongs.remove(id);
        log.info("Deleting song with id " + id);
        log.info(removedSong + " has been removed");
        return ResponseEntity.ok(new DeleteSongResponseDto("You have deleted song with id " + id, HttpStatus.OK));
    }
    @PutMapping("/{id}")
    public ResponseEntity <UpdateSongResponseDto> replaceSong(@RequestBody @Valid UpdateSongRequestDto song,
                                                              @PathVariable Integer id){
        Map<Integer, SongEntity> allSongs = songRetriever.findAll();
        if(!allSongs.containsKey(id)){
            throw new SongNotFoundException("Song not found");
        }
        String songName = song.songName();
        String artistName = song.artistName();
        SongEntity oldSong = allSongs.put(id, new SongEntity(songName, artistName));
        log.info(oldSong.name() + " has been replaced with " + songName);
        return ResponseEntity.ok(new UpdateSongResponseDto(new SongEntity(songName, artistName)));
    }
//    @PatchMapping("/songs/{id}")
//    public ResponseEntity <PartiallyUpdateSongResponseDto> patchSong(@RequestBody @Valid PartiallyUpdateSongRequestDto song,
//                                                                     @PathVariable Integer id){
//        if(!database.containsKey(id)){
//            throw new SongNotFoundException("Song not found");
//        }
//        String songName = song.songName();
//        String artistName = song.artistName();
//        SongEntity oldSong = database.put(id, new SongEntity(songName, artistName));
//        log.info(oldSong.name() + " has been replaced with " + songName);
//        return ResponseEntity.ok(new UpdateSongResponseDto(new SongEntity(songName, artistName)));
//    }
}
