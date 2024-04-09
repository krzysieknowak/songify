package com.songify.infrastructure.crud.artist.controller;

import com.songify.domain.crud.SongifyCrudFacade;
import com.songify.domain.crud.dto.ArtistDto;
import com.songify.domain.crud.dto.ArtistRequestDto;
import com.songify.infrastructure.crud.artist.dto.AllArtistsDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/artists")
public class ArtistController {

    private final SongifyCrudFacade crudFacade;

    @PostMapping
    public ResponseEntity<ArtistDto> postArtist(@RequestBody ArtistRequestDto requestDto) {
        ArtistDto artistDto = crudFacade.addArtist(requestDto);
        return ResponseEntity.ok(artistDto);
    }
    @GetMapping
    public ResponseEntity<AllArtistsDto> getArtists(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Set<ArtistDto> artists = crudFacade.findAllArtists(pageable);
        AllArtistsDto artistsDto = new AllArtistsDto(artists);
        return ResponseEntity.ok(artistsDto);
    }

}
