package com.songify.song.domain.repository;

import com.songify.song.domain.model.SongEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Map;

public interface SongRepository extends Repository<SongEntity, Long> {

    List<SongEntity> findAll();
    SongEntity save(SongEntity song);
}
