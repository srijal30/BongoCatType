import java.util.Scanner;
import java.util.LinkedList;

//create methods to get valid input (INPUT HANDLERS)

public class TerminalGame {

    private GameManager manager;
    private Scanner input; 

    //Start a Game on Construction 
    public TerminalGame(){
        input = new Scanner( System.in );
        manager = new GameManager();
        setup();
        startGame();
    }

    //sets a game up (by getting difficulty and wordCount)
    public void setup(){
        System.out.print("Select difficulty from 0 to 3: ");
        WordGenerator.difficulty = input.nextInt() ;

        System.out.print("Select amount of words you would like the test to be: ");
        manager.setWordCount( input.nextInt() );

        manager.newGame();
    }

    public void startGame(){
        System.out.println("\nTHIS IS YOUR PROMPT:");
       
        //print out the prompt
        for( String word : manager.getWords() ){
            System.out.print( word + " ");
        }

        System.out.println("\n\nPRESS ENTER WHEN YOU ARE READY");
        input.nextLine();
        input.nextLine();

        System.out.println("\nBEGIN TYPING:");
        manager.startTimer(); //start the timer
        
        //tmporary setup to get the user input
        String[] userInput = input.nextLine().split(" ");
        LinkedList<String> temp = new LinkedList<String>();
        for( String word : userInput ){
            temp.add( word );
        }
        manager.setUserWords( temp );

        //print out the results
        System.out.println("\nYOUR STATS:");
        System.out.println("RAW: " +  manager.rawWPM() );
        System.out.println("REAL: " +  manager.realWPM() );
        System.out.println("ACC: " + manager.accuracy() * 100 + "%"  );
    }
}
