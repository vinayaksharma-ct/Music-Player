import java.text.*;
import java.util.*;

public class Album {
    Map<String, List<Song>> song;
    List<Song> songs;

    public Album() {
        song = new HashMap<>();
        songs = new ArrayList<>();
    }

    public void addSong(String title, String Artist_Name) {
        try {
            Song s = new Song(title, Artist_Name);
            songs.add(s);
            song.putIfAbsent(Artist_Name, new ArrayList<>());
            song.get(Artist_Name).add(s);
            System.out.println("Song added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding song: " + e.getMessage());
        }
    }

    public void playSong(String title, String Artist_Name) {
        try {
            boolean found = false;
            for (Song s : songs) {
                if (s.title.equals(title) && s.Artist_Name.equals(Artist_Name)) {
                    System.out.println("Playing: " + s);
                    s.play();
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new Exception("Song not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getTop10songs() {
        System.out.println("Top 10 songs of all time:");
        try {
            if (songs.isEmpty()) {
                throw new Exception("No songs found.");
            }

            Collections.sort(songs, new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return o2.counter - o1.counter;
                }
            });

            int limit = Math.min(10, songs.size());
            for (int i = 0; i < limit; i++) {
                System.out.println(songs.get(i));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getTop10songsArtist() {
        System.out.println("Enter artist name here:");
        try (Scanner scanner = new Scanner(System.in)) {
            String Artist_Name = scanner.nextLine();
            List<Song> ArtistSongs = song.get(Artist_Name);

            if (ArtistSongs == null || ArtistSongs.isEmpty()) {
                throw new Exception("No songs found for the artist: " + Artist_Name);
            }
            Collections.sort(ArtistSongs, new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return o2.counter - o1.counter;
                }
            });
            int limit = Math.min(10, ArtistSongs.size());
            System.out.println("Top " + limit + " songs for artist: " + Artist_Name);
            for (int i = 0; i < limit; i++) {
                System.out.println(ArtistSongs.get(i));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getTopSong5days() {
        System.out.println("Top 10 songs for the last 5 days:");
        try {
            List<Song> dateSongs = new ArrayList<>();
            Date fiveDaysAgo = new Date(System.currentTimeMillis() - 5L * 24 * 60 * 60 * 1000);
            for (Song s : songs) {
                for (Date playDate : s.date) {
                    if (!playDate.before(fiveDaysAgo)) { 
                        dateSongs.add(s);
                        break;
                    }
                }
            }
            if (dateSongs.isEmpty()) {
                System.out.println("No songs found in the last 5 days.");
                return;
            }
            dateSongs.sort((o1, o2) -> o2.counter - o1.counter);
            int limit = Math.min(10, dateSongs.size());
            System.out.println("Displaying top " + limit + " songs:");
            for (int i = 0; i < limit; i++) {
                System.out.println(dateSongs.get(i));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }   
    }

    public void artistless10time() {
        System.out.println("List of artists played less than 10 times:");
        try {
            boolean found = false;
            for (Map.Entry<String, List<Song>> entry : song.entrySet()) {
                int count = 0;
                for (Song s : entry.getValue()) {
                    count += s.counter;
                }
                if (count < 10) {
                    System.out.println(entry.getKey());
                    found = true;
                }
            }
            if (!found) {
                throw new Exception("No artists found with less than 10 plays.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
