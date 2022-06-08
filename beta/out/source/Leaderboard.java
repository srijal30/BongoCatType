import java.util.ArrayList;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileOutputStream;

import java.net.URL;
import java.net.HttpURLConnection;

public class Leaderboard {
    private static ArrayList<Entry> entries;
    //attempts to add the the new entry both locally and globally
    public static void addEntry( String name, double real, double acc, double raw){
        String entryString = name+","+real+","+acc+","+raw+"\n";
        //add to local
        try{
            FileOutputStream local = new FileOutputStream("local.csv", true);
            local.write( entryString.getBytes()  );
            local.close();
        } catch ( Exception e ){ System.out.println("AN ERROR OCCURED WHILE ADDING ENTRY LOCALLY"); }
        //add to global
        try{
            URL url = new URL( "https://BongoCatTypeServer.srijal30.repl.co/new/" + entryString );
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.getResponseCode(); //we dont care about the message when adding entry
        } catch (Exception e ){ System.out.println("AN ERROR OCCURED WHILE ADDING THE ENTRY GLOBALLY");}
    }
    
    public static ArrayList<Entry> localLeaderboard(){
        //clear previous entries
        entries = new ArrayList<>();
        //read from file
        try{
            Scanner fileReader = new Scanner(new File("local.csv") );
            while( fileReader.hasNext() ){
                String[] info = fileReader.nextLine().split(",");
                String name = info[0];
                double real = Double.parseDouble( info[1] );
                double acc = Double.parseDouble( info[2] );
                double raw = Double.parseDouble( info[3] );
                entries.add( new Entry(name, real, acc, raw) );
            }
        } catch (Exception e){ System.out.println("ERROR OCCURED WHILE GETTING LOCAL LEADERBOARD!"); }
        sortEntries();
        return entries;
    }
    
    public static ArrayList<Entry> globalLeaderboard(){
        //clear previous entries
        entries = new ArrayList<>();
        //read from file
        try{
            URL url = new URL("https://BongoCatTypeServer.srijal30.repl.co/entries");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader ( new InputStreamReader( connection.getInputStream() ) );
            String nextLine = in.readLine();
            while( nextLine != null ){
                String[] info = nextLine.split(",");
                String name = info[0];
                double real = Double.parseDouble( info[1] );
                double acc = Double.parseDouble( info[2] );
                double raw = Double.parseDouble( info[3] );
                entries.add( new Entry(name, real, acc, raw) );
                nextLine = in.readLine();
            }
        } catch (Exception e){ System.out.println("ERROR OCCURED WHILE GETTING GLOBAL LEADERBOARD!"); }
        sortEntries();
        return entries;
    }
    
    //sorts the entries using quick sort (we want to sort in descending order)
    public static void sortEntries(){
        sortHelper(0, entries.size()-1);
    }
    private static void sortHelper( int start, int end ){
        if( (end-start) < 1 ) return;
        swap( start, end );
        int swapPos = start;
        for( int i = 0; i < end; i++ ){
            if( entries.get(i).compareTo( entries.get(end) ) > 0 ) swap( i, swapPos++ );   
        }
        swap( swapPos, end );
    }
    private static void swap( int a, int b){
        Entry tmp = entries.get(a);
        entries.set(a, entries.get(b) );
        entries.set(b, tmp);
    }
}

//class the defines a leaderboard entry
//will look like this name,real,acc,raw
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
