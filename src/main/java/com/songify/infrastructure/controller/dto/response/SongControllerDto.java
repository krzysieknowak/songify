package com.songify.infrastructure.controller.dto.response;

import lombok.Builder;

@Builder
public record SongControllerDto(Long id, String name, String artist) {
}
