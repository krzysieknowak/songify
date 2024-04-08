package com.songify.infrastructure.crud.song.controller;

import com.songify.domain.crud.dto.SongEntityDto;
import com.songify.infrastructure.crud.song.controller.dto.response.DeleteSongResponseDto;
import com.songify.infrastructure.crud.song.controller.dto.response.PartiallyUpdateSongResponseDto;
import com.songify.infrastructure.crud.song.controller.dto.response.SongControllerDto;
import com.songify.infrastructure.crud.song.controller.dto.response.SongResponseDto;
import com.songify.infrastructure.crud.song.controller.dto.response.SongsResponseDto;
import com.songify.infrastructure.crud.song.controller.dto.response.UpdateSongResponseDto;
import org.springframework.http.HttpStatus;


import java.util.List;

public class SongControllerMapper {
    public static SongsResponseDto mapFromSongsToSongsResponseDto(List<SongEntityDto> allSongs) {
        List<SongControllerDto> songControllerDtos = allSongs.stream()
                .map(songEntityDto -> SongControllerDto.builder()
                        .id(songEntityDto.id())
                        .name(songEntityDto.name())
                        .artist(songEntityDto.artist())
                        .build())
                .toList();
        return new SongsResponseDto(songControllerDtos);
    }
    public static SongResponseDto mapFromSongEntityDtoToSongResponseDto(final SongEntityDto song) {
        SongControllerDto songControllerDto = SongControllerDto.builder()
                .id(song.id())
                .name(song.name())
                .artist(song.artist())
                .build();
        return new SongResponseDto(songControllerDto);
    }

    public static SongResponseDto mapFromSongControllerDtoToSongResponseDto(SongControllerDto song) {
        return new SongResponseDto(song);
    }
    public static UpdateSongResponseDto mapFromSongEntityDtoToUpdateSongResponseDto(SongEntityDto song) {
        SongControllerDto songControllerDto = SongControllerDto.builder()
                .name(song.name())
                .artist(song.artist())
                .build();
        return new UpdateSongResponseDto(songControllerDto);
    }
    public static PartiallyUpdateSongResponseDto mapFromSongControllerDtoToPartiallyUpdateSongResponseDto(SongControllerDto song) {
       return new PartiallyUpdateSongResponseDto(song);
    }
    public static DeleteSongResponseDto createDeleteSongResponseDto(Long id){
        return new DeleteSongResponseDto("You have deleted song with id " + id, HttpStatus.OK);
    }
}
