import java.io.*;
import java.util.*;

public class Woo{

    public static void main(String[] args) throws FileNotFoundException{
        //load the words (in the future have a CSV)
        Scanner wordText = new Scanner( new File("default.txt") );
        while( wordText.hasNext() ){
            WordGenerator.addWord( wordText.nextLine() );
        }

        //start the terminal game
        TerminalGame game = new TerminalGame();

        //start the processing game?!?!? how would this work (NOTE SEPERATE FILE)
        //ProcessingGame game = new ProcessingGame();
    }
}
