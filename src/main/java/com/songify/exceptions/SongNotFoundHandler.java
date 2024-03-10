package com.songify.exceptions;

import com.songify.song.error.SongNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SongNotFoundHandler {

    @ExceptionHandler(SongNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public SongNotFoundHandlerDto handleSongNotFoundError(SongNotFoundException exception){
        String message = exception.getMessage();
        return new SongNotFoundHandlerDto(message, HttpStatus.NOT_FOUND);
    }
}
