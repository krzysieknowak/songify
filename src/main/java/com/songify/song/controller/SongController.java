package com.songify.song.controller;

import com.songify.song.dto.request.UpdateSongRequestDto;
import com.songify.song.dto.response.UpdateSongResponseDto;
import com.songify.song.error.SongNotFoundException;
import com.songify.song.dto.response.DeleteSongResponseDto;
import com.songify.song.dto.request.SongRequestDto;
import com.songify.song.dto.response.SongResponseDto;
import com.songify.song.dto.response.SongsResponseDto;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
public class SongController {
    Map<Integer,SongEntity> database = new HashMap<>(Map.of(
            1, new SongEntity("Umbrella", "Rihanna"),
            2, new SongEntity("Counting stars","OneRepublic"),
            3, new SongEntity("Erotyczny pif paf", "Bracia Figo Fagot"),
            4, new SongEntity("Pisarz miłości","Bracia Figo Fagot")
    ));

    @GetMapping("/songs")
    public ResponseEntity <SongsResponseDto> getSongs(){

        SongsResponseDto responseDto = new SongsResponseDto(database);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity <SongResponseDto> getSongById(@PathVariable Integer id, @RequestHeader(required = false) String requestId){
        log.info("Header: " + requestId);
        if(!database.containsKey(id)){
            throw new SongNotFoundException("Song not found");
        }
        SongEntity song = database.get(id);
        if(song == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(new SongResponseDto(song.name(), song.arist()));
    }

    @PostMapping("/songs")
    public ResponseEntity <SongResponseDto> postSong(@RequestBody @Valid SongRequestDto song){
        String name = song.songName();
        String artist = song.artistName();
        database.put(database.size() + 1, new SongEntity(name, artist));
        return ResponseEntity.ok(new SongResponseDto(name, artist));
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<DeleteSongResponseDto>deleteSongById(@PathVariable @Valid Integer id){
        if(!database.containsKey(id)){
            throw new SongNotFoundException("Song not found");
        }
        SongEntity removedSong = database.remove(id);
        log.info("Deleting song with id " + id);
        log.info(removedSong + " has been removed");
        return ResponseEntity.ok(new DeleteSongResponseDto("You have deleted song with id " + id, HttpStatus.OK));
    }
    @PutMapping("/songs/{id}")
    public ResponseEntity <UpdateSongResponseDto> replaceSong(@RequestBody @Valid UpdateSongRequestDto song,
                                                              @PathVariable Integer id){
        if(!database.containsKey(id)){
            throw new SongNotFoundException("Song not found");
        }
        String songName = song.songName();
        String artistName = song.artistName();
        SongEntity oldSong = database.put(id, new SongEntity(songName, artistName));
        log.info(oldSong.name() + " has been replaced with " + songName);
        return ResponseEntity.ok(new UpdateSongResponseDto(new SongEntity(songName, artistName)));
    }
}
