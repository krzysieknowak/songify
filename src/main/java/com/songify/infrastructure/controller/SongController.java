package com.songify.infrastructure.controller;

import com.songify.domain.crud.song.SongCrudFacade;
import com.songify.domain.crud.song.dto.SongEntityDto;
import com.songify.infrastructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songify.infrastructure.controller.dto.request.SongRequestDto;
import com.songify.infrastructure.controller.dto.request.UpdateSongRequestDto;
import com.songify.infrastructure.controller.dto.response.DeleteSongResponseDto;
import com.songify.infrastructure.controller.dto.response.PartiallyUpdateSongResponseDto;
import com.songify.infrastructure.controller.dto.response.SongControllerDto;
import com.songify.infrastructure.controller.dto.response.SongResponseDto;
import com.songify.infrastructure.controller.dto.response.SongsResponseDto;
import com.songify.infrastructure.controller.dto.response.UpdateSongResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.songify.domain.crud.song.SongEntityDomainMapper.mapFromPartiallyUpdateSongRequestDtoToSongEntityDto;
import static com.songify.domain.crud.song.SongEntityDomainMapper.mapFromSongRequestDtoToSongEntityDto;
import static com.songify.domain.crud.song.SongEntityDomainMapper.mapFromUpdateSongRequestDtoToSongEntityDto;
import static com.songify.infrastructure.controller.SongControllerMapper.createDeleteSongResponseDto;
import static com.songify.infrastructure.controller.SongControllerMapper.mapFromSongControllerDtoToSongResponseDto;
import static com.songify.infrastructure.controller.SongControllerMapper.mapFromSongControllerDtoToPartiallyUpdateSongResponseDto;
import static com.songify.infrastructure.controller.SongControllerMapper.mapFromSongEntityDtoToSongResponseDto;
import static com.songify.infrastructure.controller.SongControllerMapper.mapFromSongEntityDtoToUpdateSongResponseDto;
import static com.songify.infrastructure.controller.SongControllerMapper.mapFromSongsToSongsResponseDto;

@RestController
@Log4j2
@RequestMapping("/songs")
@AllArgsConstructor
public class SongController {

    private final SongCrudFacade songCrudFacade;

    @GetMapping
    public ResponseEntity<SongsResponseDto> getSongs(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        List<SongEntityDto> allSongs = songCrudFacade.findAll(pageable);
        SongsResponseDto songsResponseDto = mapFromSongsToSongsResponseDto(allSongs);
        return ResponseEntity.ok(songsResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongResponseDto> getSongById(@PathVariable Long id, @RequestHeader(required = false) String requestId) {
        log.info("Header: " + requestId);
        SongEntityDto song = songCrudFacade.findSongById(id);
        SongResponseDto songResponseDto = mapFromSongEntityDtoToSongResponseDto(song);
        return ResponseEntity.ok(songResponseDto);
    }

    @PostMapping
    public ResponseEntity<SongResponseDto> postSong(@RequestBody @Valid SongRequestDto request) {
        SongEntityDto song = mapFromSongRequestDtoToSongEntityDto(request);
        SongEntityDto savedSong = songCrudFacade.addSong(song);
        SongControllerDto savedSongDto = mapFromSongEntityDtoToSongControllerDto(savedSong);
        SongResponseDto response = mapFromSongControllerDtoToSongResponseDto(savedSongDto);
        return ResponseEntity.ok(response);
    }

    private SongControllerDto mapFromSongEntityDtoToSongControllerDto(final SongEntityDto songEntityDto) {
        return SongControllerDto.builder()
                .name(songEntityDto.name())
                .artist(songEntityDto.artist())
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSongResponseDto> deleteSongById(@PathVariable @Valid Long id) {
        songCrudFacade.deleteSongById(id);
        DeleteSongResponseDto deleteSongResponseDto = createDeleteSongResponseDto(id);
        return ResponseEntity.ok(deleteSongResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateSongResponseDto> replaceSong(@RequestBody @Valid UpdateSongRequestDto requestDto,
                                                             @PathVariable Long id) {
        SongEntityDto newSong = mapFromUpdateSongRequestDtoToSongEntityDto(requestDto);
        songCrudFacade.updateSongById(id, newSong);
        UpdateSongResponseDto responseDto = mapFromSongEntityDtoToUpdateSongResponseDto(newSong);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/songs/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> patchSong(@RequestBody @Valid PartiallyUpdateSongRequestDto song,
                                                                    @PathVariable Long id) {
        SongEntityDto updateSong = mapFromPartiallyUpdateSongRequestDtoToSongEntityDto(song);
        songCrudFacade.updateSongPartiallyById(id, updateSong);
        SongControllerDto updateSongDto = mapFromSongEntityDtoToSongControllerDto(updateSong);
        PartiallyUpdateSongResponseDto responseDto = mapFromSongControllerDtoToPartiallyUpdateSongResponseDto(updateSongDto);
        return ResponseEntity.ok(responseDto);
    }
}
