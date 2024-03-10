package com.songify.song.error;

import org.springframework.http.HttpStatus;

public record SongNotFoundHandlerDto(String message, HttpStatus status) {
}
