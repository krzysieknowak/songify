package com.songify.infrastructure.apivalidation;

import org.springframework.http.HttpStatus;

import java.util.List;

record ValidationResponseErrorHandlerDto(List<String> error, HttpStatus httpStatus) {
}
