package com.songify.song.domain.service;

import com.songify.song.infrastructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.request.SongRequestDto;
import com.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.response.*;
import com.songify.song.domain.model.SongEntity;
import org.springframework.http.HttpStatus;


import java.util.List;

public class SongMapper {

    public static List<SongDto> mapFromSongsToSongsDto(List<SongEntity> allSongs) {
        return allSongs.stream()
                .map(SongMapper::mapFromSongEntityToSongDto)
                .toList();
    }
    public static SongEntity mapFromSongRequestDtoToSongEntity(SongRequestDto requestDto) {
        return new SongEntity(requestDto.songName(), requestDto.artistName());
    }

    public static SongResponseDto mapFromSongDtoToSongResponseDto(SongDto song) {
        return new SongResponseDto(song);
    }
    public static SongEntity mapFromUpdateSongRequestDtoToSongEntity(UpdateSongRequestDto song) {
        return new SongEntity(song.artistName(), song.songName());

    }
    public static UpdateSongResponseDto mapFromSongEntityToUpdateSongResponseDto(SongEntity song) {
        return new UpdateSongResponseDto(song);
    }
    public static SongEntity mapFromPartiallyUpdateSongRequestDtoToSongEntity(PartiallyUpdateSongRequestDto requestDto) {
        return new SongEntity(requestDto.songName(), requestDto.artistName());
    }
    public static PartiallyUpdateSongResponseDto mapFromSongDtoToPartiallyUpdateSongResponseDto(SongDto song) {
       return new PartiallyUpdateSongResponseDto(song);
    }
    public static SongDto mapFromSongEntityToSongDto(SongEntity song) {
        return new SongDto(song.getId(), song.getName(), song.getArtist());
    }
    public static DeleteSongResponseDto createDeleteSongResponseDto(Long id){
        return new DeleteSongResponseDto("You have deleted song with id " + id, HttpStatus.OK);
    }
}
