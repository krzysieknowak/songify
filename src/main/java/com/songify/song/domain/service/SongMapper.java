package com.songify.song.domain.service;

import com.songify.song.infrastructure.controller.dto.request.SongRequestDto;
import com.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.response.SongResponseDto;
import com.songify.song.domain.model.SongEntity;
import com.songify.song.infrastructure.controller.dto.response.UpdateSongResponseDto;

public class SongMapper {

    public static SongEntity mapFromSongRequestDtoToSongEntity(SongRequestDto requestDto) {
        return new SongEntity(requestDto.songName(), requestDto.artistName());
    }

    public static SongResponseDto mapFromSongToSongResponseDto(SongEntity song) {
        SongResponseDto response = new SongResponseDto(song);
        return response;
    }
    public static SongEntity mapFromUpdateSongRequestDtoToSongEntity(UpdateSongRequestDto song) {
        SongEntity response = new SongEntity(song.artistName(), song.songName());
        return response;
    }
    public static UpdateSongResponseDto mapFromSongEntityToUpdateSongResponseDto(SongEntity song) {
        UpdateSongResponseDto updateSongResponseDto = new UpdateSongResponseDto(song);
        return updateSongResponseDto;
    }
}
