package com.songify.infrastructure.crud.song.controller.dto.response;


import java.util.List;

public record SongsResponseDto(List<SongControllerDto> songs) {
}
