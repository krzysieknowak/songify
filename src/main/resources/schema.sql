CREATE TABLE IF NOT EXISTS song
    (
    id BIGSERIAL PRIMARY KEY,
    artist VARCHAR(255) NOT NULL,
    songName VARCHAR(255) NOT NULL
    );

DROP TABLE song;

INSERT INTO song(artist, songName) VALUES ('Blanka', 'Bejba');

SELECT * FROM song;