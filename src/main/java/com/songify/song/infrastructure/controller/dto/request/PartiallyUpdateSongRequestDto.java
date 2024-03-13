package com.songify.song.infrastructure.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PartiallyUpdateSongRequestDto(
                                            @NotNull(message = "song name must not be null")
                                            @NotEmpty(message = "song name must not be empty")
                                            @NotBlank(message = "song name must not be blank")
                                            String songName,
                                            @NotNull(message = "artist name must not be null")
                                            @NotEmpty(message = "artist name must not be empty")
                                            @NotBlank(message = "artist name must not be blank")
                                            String artistName) {
}
