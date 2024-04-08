package com.songify.infrastructure.crud.song.controller.error;

import org.springframework.http.HttpStatus;

public record SongNotFoundHandlerDto(String message, HttpStatus status) {
}
