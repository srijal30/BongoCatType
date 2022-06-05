import java.util.Scanner;
import java.util.ArrayList;

public class Leaderboard {
    private static ArrayList<Entry> entries;
    //reads from file
    public static void getLocalLeaderboard(){
        //read entries

        //sort entries
        sortEntries();

        //return the entries
    }

    //gets from flask server :) (maybe we can even do a database idk)
    public static void getGlobalLeaderboard(){

    }
    private static void sortEntries(){

    }
}

//class the defines a leaderboard entry
class Entry implements Comparable<Entry> {
    private String nombre;
    private double realWPM;
    private double accuracy;
    private double rawWPM;

    public String getName(){ return nombre; }
    public double realWPM(){ return realWPM; }
    public double accuracy(){ return accuracy; }
    public double rawWPM(){ return rawWPM; }
    
    public Entry( String name, double real, double acc, double raw ){
        nombre = name; realWPM = real; accuracy = acc; rawWPM = raw; 
    }

    //FIRST COMPARE REAL, THEN ACC, THEN RAW
    public int compareTo( Entry other ) {
        if( this.realWPM > other.realWPM ) return 1;
        if( other.realWPM > this.realWPM ) return -1;
        if( this.accuracy > other.accuracy ) return 1;
        if( other.accuracy > this.accuracy ) return -1;
        if( this.realWPM > other.realWPM ) return 1;
        if ( other.realWPM > this.realWPM) return -1;
        return 0;
    }
}