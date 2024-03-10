package com.songify.exceptions;

import org.springframework.http.HttpStatus;

public record SongNotFoundHandlerDto(String message, HttpStatus status) {
}
