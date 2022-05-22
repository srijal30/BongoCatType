import java.util.*;

//we should add functionality for user to repeat the same word prompt if they want to
//also maybe we can store the prompt for the leaderboard (if we decide to add one)

public class GameManager{

    //word storage
    private LinkedList<String> originalWords;
    private LinkedList<String> userWords; //maybe we can implement a stack here
    /*
    the way we would implement a stack is for when we want real time updates 
    for WPM and stuff, that way we can have methods in the game manager to update the words
    using by pushing to the userWords stack and then comparing to original words, its kinda
    useless, but its fine

    alternatively, we can use a queue, and have an option if the user wants 1 word at a time
    */
    
    //storing the start time (in nanoseconds)
    private long startTime;


    //difficulty variables
    private /*dont know what type yet*/ int difficulty; 
    private int wordCount;

    //what should we put in our gameManager constructor?
    public GameManager( int dif, int wC ){
        difficulty = dif;
        wordCount = wC;

        //automatically setup a Game
        newGame( false );
    }


    //SETTERS

    public void setDifficulty( int diff ){ difficulty = diff; }    
    public void setWordCount( int wC ){ wordCount = wC; }


    //FUNCTIONAL
    
    //returns the word list and starts the timer
    public void start(){
        startTime = System.nanoTime();
    }
   
    //prepares gameManager for a new game to be started w/ start()
    public LinkedList<String> newGame( /*variable here for prompt repeition*/ boolean repeat){
        //clear old userWords
        userWords = new LinkedList<String>();
        //create new word prompt
        if ( !repeat ){
            generateWords();
        }
        return originalWords;
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
        //NOT IMPLEMENTED
        return 0.0;
    }

    //returns your rawWPM
    public double rawWPM(){
        //NOT IMPLEMENTED
        return 0.0;
    }

    //returns your accuracy as a percentage
    public double accuracy(){
        //NOT IMPLEMENTED
        return 0.0;
    }

}
