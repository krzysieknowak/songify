package com.songify;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
public class SongController {
    Map<Integer,String> database = new HashMap<>();

    @GetMapping("/songs")
    public ResponseEntity <SongsResponseDto> getSongs(){
        database.put(1, "Umbrella");
        database.put(2, "Counting Stars");
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
}
