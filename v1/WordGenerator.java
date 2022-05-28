import java.util.LinkedList;
import java.util.*;

public class WordGenerator{
  public static LinkedList<Word> wordList = new LinkedList<Word>();
  public static int difficulty = 3; //int value from 0-3
  
  //for now only plain word w/o previous diff
  public static void addWord( String word ){
    wordList.add( new Word( word ) );
  }

  //shuffles randomly
  public static void shuffle(){
    Collections.shuffle( wordList );
  }
  
  //sort by difficulty
  public static void sort(){
    Collections.sort( wordList );
  }

  public static void generate(int wC, LinkedList<String> queue){
    //shuffle & sort for variation but desired difficulty
    shuffle();
    sort();
    //add to queue from top
    for( int i = 0 ; i < wC; i++ ){
      queue.addLast( wordList.get(i).getValue() );
    }
  }
  
}

//IN THE FUTURE FIX THE DIFF CALCULATOR AND MAKE IT ABOUT SOMETHIGN OTHER THAN LENGTH
class Word implements Comparable<Word>{
  String value;
  double diff;
  //when adding a word w/o value
  public Word( String val ){
    value = val;
    diff = calcDiff(val);
  }
  //when adding word w/ a value
  public Word( String val, double diff ){
    value = val;
    this.diff = diff;
  }
  //FUNCTION
  public String getValue(){return value;}
  public String toString(){return value + " " + diff;}
  //for comparing to another word (based on whichever is closer to chosen difficulty)
  /* 
  to find the difficulty range (exclusive)
  diff 0 : 0-6
  diff 1 : 6-8
  diff 2 : 8-10
  diff 3:  10-22.5
  */
  public int compareTo( Word other ){
    int thisLevelDiff = Math.abs( WordGenerator.difficulty - diffLevel( this.diff ) );
    int otherLevelDiff = Math.abs( WordGenerator.difficulty - diffLevel( other.diff ) );
    if( thisLevelDiff < otherLevelDiff ) return -1;
    else if( thisLevelDiff > otherLevelDiff ) return 1;
    else return 0;
  }
  //Calculating a diff for a "new" word
  public static double calcDiff( String word ){
    return word.length();
  }
  //Calculating diff level for word
  public static int diffLevel( double d ){
    if( d < 6 ) return 0;
    else if( d < 8 ) return 1;
    else if ( d < 10 ) return 2;
    else return 3;
  }
}
