import java.util.Scanner;
import java.util.LinkedList;

//create methods to get valid input
//should we use ansi codes?

public class TerminalGame {

    private GameManager manager;
    private Scanner input; 

    //Start on construction
    public TerminalGame(){
        input = new Scanner( System.in );
        //is there a cleaner way to implement this...
        int[] setupInfo = setup(); 
        manager = new GameManager( setupInfo[0], setupInfo[1] );
        startGame();
    }

    //sets a game up (by getting difficulty and wordCount)
    public int[] setup(){
        System.out.print("Select difficulty from 0 to 5: ");
        int difficulty = input.nextInt();
        System.out.print("\nSelect amount of words you would like the test to be: ");
        int wordCount = input.nextInt();
        return new int[] {difficulty, wordCount};
    }

    public void startGame(){
        System.out.println("This is your prompt:");
        
        for( String word : manager.getWords() ){
            System.out.print( word + " ");
        }
        System.out.println("\nBegin Typing: ");
        manager.start();
        
        input.nextLine();

        String[] userInput = input.nextLine().split(" ");
        
        //temporary setup
        LinkedList<String> temp = new LinkedList<String>();
        for( String word : userInput ){
            temp.add( word );
        }
        manager.setUserWords( temp );

        System.out.println("\nYOUR STATS:");
        System.out.println("RAW: " +  manager.rawWPM() );
        System.out.println("REAL: " +  manager.realWPM() );
        System.out.println("ACC: " + manager.accuracy() * 100 + "%"  );


    }

}
