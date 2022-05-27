import java.util.*;

//we should add functionality for user to repeat the same word prompt if they want to
//also maybe we can store the prompt for the leaderboard (if we decide to add one)

public class GameManager{

    //word storage "queues"
    private LinkedList<String> originalWords;
    private LinkedList<String> userWords; 

    //store the start time
    private long startTime;

    //difficulty variables
    private int difficulty; 
    private int wordCount;

    //what should we put in our gameManager constructor?
    public GameManager(){
    }


    //SETTERS
    public void setDifficulty( int diff ){ difficulty = diff; }    
    public void setWordCount( int wC ){ wordCount = wC; }

    //tmp (hopefully)
    public void setUserWords( LinkedList<String> words ){ userWords = words; }

    //GETTERS
    //tmp (hopefully)
    public LinkedList<String> getWords(){ return originalWords; }


    //FUNCTIONAL
    //returns the word list and starts the timer
    public void startTimer(){
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
        double minutes = ( System.nanoTime() - startTime ) / 60.0 / 1000000000.0;
        return userWords.size() / minutes;
    }
    //returns your accuracy as a percentage
    public double accuracy(){
        return ( wordCount - mistakes() ) / (double)wordCount;  
    }
    //returns mistake counts (mistyped words) while preserving the queues
    public int mistakes(){
        int count = 0;
        int longest = Math.max( userWords.size(), originalWords.size() );
        int shortest = Math.min( userWords.size(), originalWords.size() );
        for( int i = 0; i < shortest; i++ ){
            //check if the words dont match
            if( !userWords.getFirst().equals( originalWords.getFirst() ) )
                count++;
            //add the word to the end
            longest--;
            userWords.addLast( userWords.removeFirst() );
            originalWords.addLast( originalWords.removeFirst() );
        }
        //find which one is shorter
        LinkedList<String> longerOne;
        boolean decrement = false;
        if ( userWords.size() <= originalWords.size() ){ 
            longerOne = originalWords;
            decrement = true;} 
        else 
            longerOne = userWords;
        //preserve the order of that one as well
        while( longest > 0 ) {
            if (decrement) count++;
            longerOne.addLast( longerOne.removeFirst() );
            longest--;
        }
        return count;
    }
}
