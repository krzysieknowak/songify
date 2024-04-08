package com.songify.infrastructure.crud.artist.controller;

import com.songify.domain.crud.SongifyCrudFacade;
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
@RequestMapping("/artist")
public class ArtistController {

    private final SongifyCrudFacade crudFacade;

    @PostMapping
    public ResponseEntity<ArtistDto> postArtist(@RequestBody ArtistRequestDto requestDto) {
        ArtistDto artistDto = crudFacade.addArtist(requestDto);
        return ResponseEntity.ok(artistDto);
    }
}
