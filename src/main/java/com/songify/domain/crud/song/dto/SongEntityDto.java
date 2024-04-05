package com.songify.domain.crud.song.dto;

import lombok.Builder;

import java.time.Instant;
@Builder
public record SongEntityDto(Long id, String name, String artist) {
}
