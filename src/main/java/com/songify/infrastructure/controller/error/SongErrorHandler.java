package com.songify.infrastructure.controller.error;

import com.songify.domain.crud.SongNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class SongErrorHandler {

    @ExceptionHandler(SongNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public SongNotFoundHandlerDto handleSongNotFoundError(SongNotFoundException exception){
        String message = exception.getMessage();
        return new SongNotFoundHandlerDto(message, HttpStatus.NOT_FOUND);
    }
}
