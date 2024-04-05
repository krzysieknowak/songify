package com.songify.domain.crud.song;

import com.songify.domain.crud.song.dto.SongEntityDto;
import com.songify.infrastructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songify.infrastructure.controller.dto.request.SongRequestDto;
import com.songify.infrastructure.controller.dto.request.UpdateSongRequestDto;

public class SongEntityDomainMapper {

    public static SongEntityDto mapFromSongRequestDtoToSongEntityDto(SongRequestDto requestDto) {
       return SongEntityDto.builder()
                .name(requestDto.songName())
                .artist(requestDto.artistName())
                .build();

    }
    public static SongEntityDto mapFromPartiallyUpdateSongRequestDtoToSongEntityDto(PartiallyUpdateSongRequestDto requestDto) {
        return SongEntityDto.builder()
                .name(requestDto.songName())
                .artist(requestDto.artistName())
                .build();
    }

    public static SongEntityDto mapFromUpdateSongRequestDtoToSongEntityDto(UpdateSongRequestDto song) {
        return SongEntityDto.builder()
                .name(song.songName())
                .artist(song.artistName())
                .build();


    }
}
