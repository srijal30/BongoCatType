import java.io.*;
import java.util.*;

public class Woo{

    public static void main(String[] args) throws FileNotFoundException{
        //MAKE THIS A THREAD IN THE FUTURE
        //add the words from text file
        Scanner wordText = new Scanner( new File("message.txt") );
        while( wordText.hasNext() ){
            WordGenerator.addWord( wordText.nextLine() );
        }
        WordGenerator.shuffle();
        System.out.println( WordGenerator.wordList );
        


        //start the terminal game
        TerminalGame game = new TerminalGame();

        //start the processing game?!?!? how would this work
        //ProcessingGame game = new ProcessingGame();
    }
}
