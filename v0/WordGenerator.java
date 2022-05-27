import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;

public class WordGenerator{


    public static void main( String[] args ) throws FileNotFoundException {

        Scanner reader = new Scanner( new File("message.txt") );
        
        String next = reader.nextLine();

        while( reader.hasNext() ){
            System.out.println( next );
            next = reader.nextLine();
        }

    }


}

class WordQueue extends PriorityQueue {

    int difficulty;  

}


