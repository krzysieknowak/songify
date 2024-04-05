package com.songify.infrastructure.controller.dto.response;


import java.util.List;

public record SongsResponseDto(List<SongControllerDto> songs) {
}
