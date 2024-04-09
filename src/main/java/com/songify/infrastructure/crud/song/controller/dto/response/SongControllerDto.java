package com.songify.infrastructure.crud.song.controller.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record SongControllerDto(Long id, String name, Instant releaseDate, Long duration) {
}
