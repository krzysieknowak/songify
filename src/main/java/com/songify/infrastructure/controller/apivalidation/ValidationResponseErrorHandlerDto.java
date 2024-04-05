package com.songify.infrastructure.controller.apivalidation;

import org.springframework.http.HttpStatus;

import java.util.List;

record ValidationResponseErrorHandlerDto(List<String> error, HttpStatus httpStatus) {
}
