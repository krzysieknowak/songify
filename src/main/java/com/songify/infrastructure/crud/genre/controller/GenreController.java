package com.songify.infrastructure.crud.genre.controller;

import com.songify.domain.crud.SongifyCrudFacade;
import com.songify.domain.crud.dto.ArtistDto;
import com.songify.domain.crud.dto.ArtistRequestDto;
import com.songify.domain.crud.dto.GenreDto;
import com.songify.domain.crud.dto.GenreRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genre")
@AllArgsConstructor
public class GenreController {
    private final SongifyCrudFacade crudFacade;
    @PostMapping
    public ResponseEntity<GenreDto> postGenre(@RequestBody GenreRequestDto requestDto) {
        GenreDto genreDto = crudFacade.addGenre(requestDto);
        return ResponseEntity.ok(genreDto);
    }
}
