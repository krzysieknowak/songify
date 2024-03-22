package com.songify.song.infrastructure.controller.dto.response;

import com.songify.song.domain.model.SongEntity;

import java.util.List;

public record SongsResponseDto(List<SongEntity> songs) {
}
