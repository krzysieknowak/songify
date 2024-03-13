package com.songify.song.domain.service;

import com.songify.song.infrastructure.controller.dto.request.SongRequestDto;
import com.songify.song.infrastructure.controller.dto.response.SongResponseDto;
import com.songify.song.domain.model.SongEntity;

public class SongMapper {

    public static SongEntity mapFromSongRequestDtoToSongEntity(SongRequestDto requestDto) {
        return new SongEntity(requestDto.songName(), requestDto.artistName());
    }

    public static SongResponseDto mapFromSongToSongResponseDto(SongEntity song) {
        SongResponseDto response = new SongResponseDto(song);
        return response;
    }
}
