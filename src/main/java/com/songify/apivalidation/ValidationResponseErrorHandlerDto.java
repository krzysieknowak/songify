package com.songify.apivalidation;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ValidationResponseErrorHandlerDto(List<String> error, HttpStatus httpStatus) {
}
