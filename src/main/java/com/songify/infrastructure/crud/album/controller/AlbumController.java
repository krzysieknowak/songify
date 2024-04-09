package com.songify.infrastructure.crud.album.controller;

import com.songify.domain.crud.SongifyCrudFacade;
import com.songify.domain.crud.dto.AlbumDto;
import com.songify.domain.crud.dto.AlbumRequestDto;
import com.songify.domain.crud.dto.ArtistDto;
import com.songify.domain.crud.dto.ArtistRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/album")
public class AlbumController {

    private final SongifyCrudFacade crudFacade;

    @PostMapping
    public ResponseEntity<AlbumDto> postAlbum(@RequestBody AlbumRequestDto requestDto) {
        AlbumDto albumDto = crudFacade.addAlbumWithOneSong(requestDto);
        return ResponseEntity.ok(albumDto);
    }
}
