package com.songify.domain.crud;

import com.songify.domain.crud.dto.ArtistDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Set;

public interface AritstRepository extends Repository<Artist,Long> {
    Artist save(Artist artist);

//    @Query("SELECT a from Artist a")
    Set<Artist> findAll(Pageable pageable);
}
