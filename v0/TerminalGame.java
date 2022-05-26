import java.util.Scanner;

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

    }

}
