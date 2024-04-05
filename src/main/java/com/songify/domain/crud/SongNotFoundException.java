package com.songify.domain.crud;

public class SongNotFoundException extends RuntimeException {

     SongNotFoundException(String message) {
        super(message);
    }
}
