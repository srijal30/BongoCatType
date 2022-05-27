import java.util.*;

//we should add functionality for user to repeat the same word prompt if they want to
//also maybe we can store the prompt for the leaderboard (if we decide to add one)

public class GameManager{

    //word storage queues
    private LinkedList<String> originalWords;
    private LinkedList<String> userWords; //maybe we can implement a stack here

    //store the start time
    private long startTime;


    //difficulty variables
    private /*dont know what type yet*/ int difficulty; 
    private int wordCount;

    //what should we put in our gameManager constructor?
    public GameManager( int dif, int wC ){
        difficulty = dif;
        wordCount = wC;
        //automatically setup a Game (we need to put this somewhere else)
        newGame();
    }


    //SETTERS

    public void setDifficulty( int diff ){ difficulty = diff; }    
    public void setWordCount( int wC ){ wordCount = wC; }
    public void setUserWords( LinkedList<String> words ){ userWords = words; }

    //GETTERS

    public LinkedList<String> getWords(){ return originalWords; }


    //FUNCTIONAL
    
    //returns the word list and starts the timer
    public void start(){
        startTime = System.nanoTime();
    }
   
    //prepares gameManager for a new game to be started w/ start()
    public void newGame(){
        //clear old userWords
        userWords = new LinkedList<String>();
        //generate new words
        generateWords();
    }

    //generate words according to difficulty
    public void generateWords(){
        //NOT IMPLEMENTED: for now simple words gen
        originalWords = new LinkedList<String>();
        String[] tmpWords = new String[]{"hello", "world", "how", "are", "you", "doing"}; 
        for( String word : tmpWords ) originalWords.add( word );
    }


    //SCORE CALCULATION
    //returns your WPM taken into account with your accuracy
    public double realWPM(){
        return rawWPM() * accuracy();
    }
    //returns your rawWPM
    public double rawWPM(){
        double minutes = ( System.nanoTime() - startTime ) / 60 / 1000000000l;
        System.out.println( minutes );
        return userWords.size() / minutes ;
    }
    //returns your accuracy as a percentage
    public double accuracy(){
        return ( wordCount - mistakes() ) / wordCount;  
    }
    //returns mistake counts (mistyped words)
    public int mistakes(){
        
        //save this info
        int wordsLeft = originalWords.size();
        int mistake = 0;

        //do stuff
        while( !userWords.isEmpty() ){
            String tmpWord = originalWords.removeFirst();
            originalWords.addLast(tmpWord);
            if( tmpWord != userWords.removeFirst() ) mistake++; 
            wordsLeft--;
        }

        //other stuff
        while( wordsLeft-- > 0 ) originalWords.addLast( originalWords.removeFirst() );
        return mistake;
    }
    

}
