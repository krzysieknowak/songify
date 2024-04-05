package com.songify.domain.crud.dto;

import lombok.Builder;

@Builder
public record SongEntityDto(Long id, String name, String artist) {
}
