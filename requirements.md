Application for managing songs, artists albums and genres
It is required to be able to:
-display songs, artists, albums, genres
-add songs, artists, albums, genres
-update songs, artists, albums, genres
-edit partially songs, artists, albums, genres

HAPPY PATH
User adds album Encore by Eminem with songs "Evil Deeds" and "Never enough".
The genre is Rap.

No songs,albums,genres or artists are in application database.

1. When I go to /songs (GET) I see no songs.
2. When I go to /genres (GET) I see no genres.
3. When I go to /albums (GET) I see no albums.
4. When I go to /artists (GET) I see no artists.
5. When I post /songs (POST) song with title "Never enough" and "Evil deeds"
   two songs are returned with ids 1 and 2
6. When I go to /songs(GET) I see two songs with their properties (artist name, album name,
   genre)