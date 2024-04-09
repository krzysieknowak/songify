package com.songify.domain.crud.dto;

import java.time.Instant;

public record AlbumDto(Long id, String title, Instant releaseDate) {
}
