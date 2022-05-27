import java.util.LinkedList;
import java.util.*;

public class WordGenerator{

  public static LinkedList<String> wordList = new LinkedList<String>();

  


  public static void addWord( String word ){
    wordList.add( word ); 
  }
  
  public static void shuffle(){
    Collections.shuffle( wordList );
  }

  public static void generate(int x, int y, LinkedList z){

  }

}

class Word {

  String value;
  int diff;

  //Maybe store current in here or somumtljsldjf 
  public Word( String val ){
    value = val;
    diff = calcDiff(val);
  }

  public static double calcDiff( String word ){
    return value.length();
  }

}
