import java.text.*;
import java.util.*;
import java.util.Scanner;
public class Album {
    Map<String,List<Song>>song;
    List<Song>songs;

    public Album(){
        song = new HashMap<>();
        songs = new ArrayList<>();
    }
    public void addSong(String title, String Artist_Name){
        Song s = new Song(title,Artist_Name);
        songs.add(s);
        if(song.containsKey(Artist_Name)){
            song.get(Artist_Name).add(s);
        }
        else{
            List<Song>ArtistSongs = new ArrayList<>();
            ArtistSongs.add(s);
            song.put(Artist_Name,ArtistSongs);
        }
    }
    

    public void playSong(String title, String Artist_Name){
        boolean found = false;
        for(Song s: songs){
            if(s.title.equals(title) && s.Artist_Name.equals(Artist_Name)){
                System.out.println("Playing " + s);
                s.play();
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Song not found");
        }
    }

    public void getTop10songs(){
        System.out.println("Top 10 songs of all time");
        if(songs.isEmpty()){
            System.out.println("No songs found");
            return;
        }
        if(songs.size()<10){
            System.out.println("Sorry there are less than 10 songs.");
            return;
        }
        // comparator function
        Collections.sort(songs, new Comparator<Song>(){
            @Override
            public int compare(Song o1, Song o2){
                return o2.counter - o1.counter;
            }
        });
        for(int i=0;i<10;i++){
            System.out.println(songs.get(i));
        }
    }

    public void getTop10songsArtist(){
        System.out.println("Enter artist name here");
        Scanner scanner = new Scanner(System.in);
        String Artist_Name = scanner.nextLine();
        List<Song>ArtistSongs = song.get(Artist_Name);
        if(ArtistSongs == null){
            System.out.println("No songs found");
            return;
        }
        if(ArtistSongs.size()<10){
            System.out.println("Sorry there are less than 10 songs of your artist");
            return;
        }
        Collections.sort(ArtistSongs, new Comparator<Song>(){
            @Override
            public int compare(Song o1, Song o2){
                return o2.counter - o1.counter;
            }
        });
        for(int i=0;i<10;i++){
            System.out.println(ArtistSongs.get(i));
        }
    }
    

    public void getTopSong5days(){
        System.out.println("Top 10 songs for last 5 days");
        List<Song>dateSongs = new ArrayList<>();
        for(Song s: songs){
            for(Date d1: s.date){
                if(d1.after(new Date(System.currentTimeMillis()-5*24*60*60*1000))){
                    dateSongs.add(s);
                    break;
                }
            }
        }
        if(dateSongs.isEmpty()){
            System.out.println("No songs found");
            return;
        }
        if(dateSongs.size()<10){
            System.out.println("Less than 10 songs found");
            return;
        }
        Collections.sort(dateSongs, new Comparator<Song>(){
            @Override
            public int compare(Song o1, Song o2){
                return o2.counter - o1.counter;
            }
        });
        for(int i=0;i<10;i++){
            System.out.println(dateSongs.get(i));
        }
    }

    public void artistless10time(){
        System.out.println("List artist played less than 10 times");
        for(Map.Entry<String,List<Song>> entry : song.entrySet()){
            int count = 0;
            for(Song s: entry.getValue()){
                count+=s.counter;
            }
            if(count<10){
                System.out.println(entry.getKey());
            }
        }
    }


}