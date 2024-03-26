package com.songify.song.infrastructure.controller;

import com.songify.song.domain.service.SongAdder;
import com.songify.song.domain.service.SongDeleter;
import com.songify.song.domain.service.SongRetriever;
import com.songify.song.domain.service.SongUpdater;
import com.songify.song.infrastructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.response.*;
import com.songify.song.infrastructure.controller.dto.request.SongRequestDto;
import com.songify.song.domain.model.SongEntity;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.songify.song.domain.service.SongMapper.*;

@RestController
@Log4j2
@RequestMapping("/songs")
@AllArgsConstructor
public class SongController {

    private final SongAdder songAdder;
    private final SongRetriever songRetriever;
    private final SongDeleter songDeleter;
    private final SongUpdater songUpdater;



    @GetMapping
    public ResponseEntity <SongsResponseDto> getSongs(@PageableDefault(page = 0, size = 10)Pageable pageable){
        List<SongEntity> allSongs = songRetriever.findAll(pageable);
        List<SongDto> allSongsDto = mapFromSongsToSongsDto(allSongs);
        SongsResponseDto responseDto = new SongsResponseDto(allSongsDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity <SongResponseDto> getSongById(@PathVariable Long id, @RequestHeader(required = false) String requestId){
        log.info("Header: " + requestId);
        SongEntity song = songRetriever.findSongById(id);
        SongDto songDto = mapFromSongEntityToSongDto(song);
        SongResponseDto songResponseDto = mapFromSongDtoToSongResponseDto(songDto);
        return ResponseEntity.ok(songResponseDto);
    }

    @PostMapping
    public ResponseEntity <SongResponseDto> postSong(@RequestBody @Valid SongRequestDto request){
        SongEntity song = mapFromSongRequestDtoToSongEntity(request);
        SongEntity savedSong = songAdder.addSong(song);
        SongDto savedSongDto = mapFromSongEntityToSongDto(savedSong);
        SongResponseDto response = mapFromSongDtoToSongResponseDto(savedSongDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSongResponseDto>deleteSongById(@PathVariable @Valid Long id){
        songDeleter.deleteSongById(id);
        DeleteSongResponseDto deleteSongResponseDto = createDeleteSongResponseDto(id);
        return ResponseEntity.ok(deleteSongResponseDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity <UpdateSongResponseDto> replaceSong(@RequestBody @Valid UpdateSongRequestDto requestDto,
                                                              @PathVariable Long id){
        SongEntity newSong = mapFromUpdateSongRequestDtoToSongEntity(requestDto);
        songUpdater.updateSongById(id, newSong);
        UpdateSongResponseDto responseDto = mapFromSongEntityToUpdateSongResponseDto(newSong);
        return ResponseEntity.ok(responseDto);
    }
    @PatchMapping("/songs/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> patchSong(@RequestBody @Valid PartiallyUpdateSongRequestDto song,
                                                                   @PathVariable Long id){
        SongEntity updateSong = mapFromPartiallyUpdateSongRequestDtoToSongEntity(song);
        songUpdater.updateSongPartiallyById(id, updateSong);
        SongDto updateSongDto = mapFromSongEntityToSongDto(updateSong);
        PartiallyUpdateSongResponseDto responseDto = mapFromSongDtoToPartiallyUpdateSongResponseDto(updateSongDto);
        return ResponseEntity.ok(responseDto);
    }
}
