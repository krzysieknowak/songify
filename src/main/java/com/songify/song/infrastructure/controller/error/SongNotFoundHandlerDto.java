package com.songify.song.infrastructure.controller.error;

import org.springframework.http.HttpStatus;

public record SongNotFoundHandlerDto(String message, HttpStatus status) {
}
