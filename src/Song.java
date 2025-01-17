import java.lang.reflect.Array;
import java.util.*;
import java.util.Scanner;
public class Song {
    String title;
    String Artist_Name;
    int counter=0;
    List<Date> date;

    public Song(String title,String Artist_Name){
        this.title = title;
        this.Artist_Name = Artist_Name;
        this.counter = 0;
        this.date= new ArrayList<>();
    }
    public void play(){
        counter++;
        date.add(new Date());
    }
    // public string getTitle(){
    //     return title;
    // }
    // public string getArtist_Name(){
    //     return Artist_Name;
    // }
    @Override
    public String toString() {
        return title + " by " + Artist_Name + " (Played: " + counter + " times)";
    }
}