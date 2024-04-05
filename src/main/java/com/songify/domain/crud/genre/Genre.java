package com.songify.domain.crud.genre;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
class Genre {

    @Id
    @GeneratedValue(generator = "song_id_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name ="song_id_seq",
            sequenceName = "song_id_seq",
            allocationSize = 1)
    private Long id;
    private String name;
}
