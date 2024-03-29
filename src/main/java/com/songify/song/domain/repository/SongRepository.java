package com.songify.song.domain.repository;

import com.songify.song.domain.model.SongEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends Repository<SongEntity, Long> {

    @Query("SELECT s FROM SongEntity s")
    List<SongEntity> findAll(Pageable pageable);
    SongEntity save(SongEntity song);

    @Query("SELECT s FROM SongEntity s WHERE s.id=:id")
    Optional<SongEntity> findById (Long id);
    @Query("DELETE FROM SongEntity s WHERE s.id=:id")
    void deleteById(Long id);
    @Modifying
    @Query("UPDATE SongEntity s SET s.name = :#{#song.name}, s.artist = :#{#song.artist} WHERE s.id = :id")
    void updateById(Long id, SongEntity song);

}
