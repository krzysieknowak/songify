package com.songify.song.domain.repository;

import com.songify.song.domain.model.SongEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class SongRepository {
    Map<Integer, SongEntity> database = new HashMap<>(Map.of(
            1, new SongEntity("Umbrella", "Rihanna"),
            2, new SongEntity("Counting stars","OneRepublic"),
            3, new SongEntity("Erotyczny pif paf", "Bracia Figo Fagot"),
            4, new SongEntity("Pisarz miłości","Bracia Figo Fagot")
    ));

    public SongEntity saveToDatabase(SongEntity song) {
        database.put(database.size() + 1, song);
        return song;
    }
    public Map<Integer,SongEntity> findAll() {
        return database;
    }
}
