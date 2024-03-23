//package com.songify.song.domain.repository;
//
//import com.songify.song.domain.model.SongEntity;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class InMemorySongRepository implements SongRepository {
//    Map<Integer, SongEntity> database = new HashMap<>(Map.of(
//            1, new SongEntity("Umbrella", "Rihanna"),
//            2, new SongEntity("Counting stars","OneRepublic"),
//            3, new SongEntity("Erotyczny pif paf", "Bracia Figo Fagot"),
//            4, new SongEntity("Pisarz miłości","Bracia Figo Fagot")
//    ));
//    @Override
//    public SongEntity save(SongEntity song) {
//        database.put(database.size() + 1, song);
//        return song;
//    }
//
//    @Override
//    public SongEntity findById(Long id) {
//        return null;
//    }
//
//    @Override
//    public List<SongEntity> findAll() {
//        return database.values().stream().toList();
//    }
//}
