import java.util.*;
import  java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Album album = new Album();

        while (true){
            System.out.println("Select any of the following options");
            System.out.println("1. Add a song");
            System.out.println("2. Play a song");
            System.out.println("3. Retrieve top 10 songs of all time");
            System.out.println("4. Retrieve top 10 songs for a particular artist");
            System.out.println("5. Retrieve top 10 songs for last 5 days");
            System.out.println("6. List artist played less than 10 times");
            System.out.println("7. None of the above");
            System.out.println("Make a choice");
            int choice =scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    System.out.println("Enter songs name to add");
                    String name = scanner.nextLine();
                    System.out.println("Enter Artist Name here");
                    String artist = scanner.nextLine();
                    album.addSong(name,artist);
                    break;
                case 2:
                    System.out.println("Enter songs name to play");
                    String name1 = scanner.nextLine();
                    System.out.println("Enter Artist Name here");
                    String artist1 = scanner.nextLine();
                    album.playSong(name1,artist1);
                    break;
                case 3:
                    album.getTop10songs();
                    break;
                case 4:
                    System.out.println("Enter artist name here");
                    String artist2 = scanner.nextLine();
                    album.getTop10songsArtist();
                    break;
                case 5:
                    album.getTopSong5days();
                    break;
                case 6:
                    album.artistless10time();
                    break;
                case 7:
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}