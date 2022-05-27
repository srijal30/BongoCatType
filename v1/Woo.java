import java.io.*;
import java.util.*;

public class Woo{

    public static void main(String[] args) throws FileNotFoundException{
        //add the words from text file
        Scanner wordText = new Scanner( new File("message.txt") );
        while( wordText.hasNext() ){
            WordGenerator.addWord( wordText.nextLine() );
        }
        //FOR TESTING IF IT WORKS
        //WordGenerator.printWords();

        //start the terminal game
        TerminalGame game = new TerminalGame();

        //start the processing game?!?!? how would this work
        //ProcessingGame game = new ProcessingGame();
    }
}
