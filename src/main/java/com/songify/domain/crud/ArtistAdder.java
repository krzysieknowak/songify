package com.songify.domain.crud;

import com.songify.domain.crud.dto.ArtistDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
class ArtistAdder {
    private final AritstRepository repository;
    ArtistDto addArtist(String name) {
        Artist artist = new Artist(name);
        Artist saved = repository.save(artist);
        return new ArtistDto(saved.getId(), saved.getName());
    }
}
