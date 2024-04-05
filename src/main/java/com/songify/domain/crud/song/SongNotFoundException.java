package com.songify.domain.crud.song;

public class SongNotFoundException extends RuntimeException {

     SongNotFoundException(String message) {
        super(message);
    }
}
