package com.songify.song.infrastructure.controller.dto.response;


import java.util.List;

public record SongsResponseDto(List<SongDto> songs) {
}
