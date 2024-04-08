package com.songify.domain.crud;

import org.springframework.data.repository.Repository;

public interface AritstRepository extends Repository<Artist,Long> {
    Artist save(Artist artist);
}
