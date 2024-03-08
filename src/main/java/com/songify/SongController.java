package com.songify;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
public class SongController {
    Map<Integer,String> database = new HashMap<>(Map.of(
            1, "Umbrella",
            2, "Counting stars",
            3, "Erotyczny pif paf",
            4, "Pisarz miłości"
    ));

    @GetMapping("/songs")
    public ResponseEntity <SongsResponseDto> getSongs(){

        SongsResponseDto responseDto = new SongsResponseDto(database);
        return ResponseEntity.ok(responseDto);
    }
    @GetMapping("/songs/{id}")
    public ResponseEntity <SongResponseDto> getSongById(@PathVariable Integer id, @RequestHeader(required = false) String requestId){
        log.info("Header: " + requestId);
        String song = database.get(id);
        if(song == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(new SongResponseDto(song));
    }
    @PostMapping("/songs")
    public ResponseEntity <SongResponseDto> postSong(@RequestBody SongRequestDto song){
        String name = song.songName();
        database.put(database.size() + 1, name);
        return ResponseEntity.ok(new SongResponseDto(name));
    }
}
