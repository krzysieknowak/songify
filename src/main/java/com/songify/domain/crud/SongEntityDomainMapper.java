package com.songify.domain.crud;

import com.songify.domain.crud.dto.SongDto;
import com.songify.infrastructure.crud.song.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songify.infrastructure.crud.song.controller.dto.request.SongRequestDto;
import com.songify.infrastructure.crud.song.controller.dto.request.UpdateSongRequestDto;

public class SongEntityDomainMapper {

    public static SongDto mapFromSongRequestDtoToSongDto(SongRequestDto requestDto) {
       return SongDto.builder()
                .name(requestDto.songName())
                .build();

    }
    public static SongDto mapFromPartiallyUpdateSongRequestDtoToSongEntityDto(PartiallyUpdateSongRequestDto requestDto) {
        return SongDto.builder()
                .name(requestDto.songName())
                .build();
    }

    public static SongDto mapFromUpdateSongRequestDtoToSongEntityDto(UpdateSongRequestDto song) {
        return SongDto.builder()
                .name(song.songName())
                .build();


    }
}
