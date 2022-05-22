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
    }

    public void startGame(){
    }

}
