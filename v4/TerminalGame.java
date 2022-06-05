import java.util.Scanner;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.LinkedList;

//create methods to get valid input (INPUT HANDLERS)
public class TerminalGame {
    private Scanner input;
    private int frameCount;
    
    private GameManager manager;
    private LinkedList<Boolean> colors;
    
    private boolean started; 
    private KeyEventHandler keys;
    
    //on construction a game is started 
    public TerminalGame(){
        frameCount = 0;
        input = new Scanner( System.in );
        manager = new GameManager();
        keys = new KeyEventHandler(this);
        setup();
        startGame();
    }
    
    //sets a game up (by getting difficulty and wordCount)
    public void setup(){
        //clear the old values
        colors = new LinkedList<>();
        System.out.print("Select difficulty from 0 to 3: ");
        WordGenerator.difficulty = input.nextInt() ;
        System.out.print("Select amount of words you would like the test to be: ");
        manager.newGame( input.nextInt() );
    }
    //start game + time
    public void startGame(){
        started = true;
        updateScreen();
        manager.startTimer();
    }
    //end the game and ask if player wants to play again
    public void endGame(){
        started = false;
        //show stats
        System.out.println(Ansi.CLEAR_SCREEN + Ansi.RESET);
        System.out.println("\033[" + 1 + ";" + 1 + "H");
        System.out.println( "YOU FINISHED THE GAME!\n\nYOUR STATS:\nReal WPM: " + manager.getRealWPM() + "\nRaw WPM: " + manager.getRawWPM() + "\nAccuracy: " + (manager.getAccuracy() *100 ) + "%");
        
        input = new Scanner(System.in); //why does it work when we make a new scanner?
        String choice;

        //ask if they want to add their score to leaderboard
        System.out.println("Do you want to add your score to the leaderboard? (Y/N)");
        choice = input.nextLine();
        if( choice.equals("Y") ){
            System.out.print("What is your name?: ");
            choice = input.nextLine();
            Leaderboard.addEntry(choice, manager.getRealWPM(), manager.getAccuracy(), manager.getRawWPM() );
        }        
        
        //ask if they want to see the leaderboard
        System.out.println("Do you want to see the leaderboard? (Y/N)");
        choice = input.nextLine();
        if( choice.equals("Y") ){
            System.out.println("Write G for Global or L for Local (G/L)");
            choice = input.nextLine();
            if( choice.equals("L") ){
                int counter = 1;
                for( Entry e: Leaderboard.localLeaderboard() ) System.out.printf("%d: %s\t%3.2f\t%3.2f%%\t%3.2f\n", counter++, e.getName(), e.realWPM(), e.accuracy(), e.rawWPM() );
            }
            else{
                int counter = 1;
                for( Entry e: Leaderboard.globalLeaderboard() ) System.out.printf("%d: %s\t%3.2f\t%3.2f%%\t%3.2f\n", counter++, e.getName(), e.realWPM(), e.accuracy(), e.rawWPM() );
            }
        }
        
        //ask if want to play again
        System.out.println("\nPlay again? (Y/N)");
        choice = input.nextLine();
        if ( choice.equals("Y") ) {
            setup();
            startGame();
        }
        else{
            System.exit(0);
        }
    }
    //update the screen aka the frame
    public void updateScreen(){
        frameCount++;
        System.out.println(Ansi.CLEAR_SCREEN + Ansi.RESET);
        System.out.println("\033[" + 1 + ";" + 1 + "H");
        System.out.println( "STATS: " + manager.getRawWPM() + "\t" + manager.getRealWPM() + "\t" + (manager.getAccuracy()*100) + "%\n" );
        //print out the colors
        String tmp = manager.getInput();
        for( int i = 0; i < tmp.length(); i++ ){
            //green
            if( colors.get(i) ){
                System.out.print( Ansi.color(Ansi.GREEN,Ansi.background(Ansi.BLACK))+tmp.charAt(i));
            }
            //red
            else{
                if( tmp.charAt(i) == ' '){
                    System.out.print( Ansi.color( Ansi.RED, Ansi.background(Ansi.RED))+tmp.charAt(i));
                }
                else{
                    System.out.print( Ansi.color(Ansi.RED,Ansi.background(Ansi.BLACK))+tmp.charAt(i));
                }
            }
        }
        System.out.println( Ansi.RESET + manager.getText( tmp.length() ) + "\n" );
        System.out.println( Ansi.RESET );
        animation();
    }
    //register keyboard input
    public void registerEvent( KeyEvent e ){
        if( !started ) return;
        //if shift-- ignore
        if( e.getKeyCode() == 16 ){
            return;
        }
        //if backspace
        else if( e.getKeyCode() == 8 ){
            if( manager.removeCharacter() ) colors.removeLast();
        }
        //if any other character
        else{
            colors.add( manager.pushCharacter( e.getKeyChar() ) );
        }
        if( manager.update()) endGame();
        else updateScreen();
    }
    //bongo cat animatino
    public void animation(){
        if( frameCount % 2 == 0){
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣷⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⠀⠀⣀⣶⣿⣿⣿⣿⣿⣿⣷⣶⣶⣶⣦⣀⡀⠀⢀⣴⣇⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⠀⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀");
            System.out.println("⠀⠀⠀⣴⣿⣿⣿⣿⠛⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣄⠀⠀⠀");
            System.out.println("⠀⠀⣾⣿⣿⣿⣿⣿⣶⣿⣯⣭⣬⣉⣽⣿⣿⣄⣼⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠀");
            System.out.println("⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄");
            System.out.println("⢸⣿⣿⣿⣿⠟⠋⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠁⣿⣿⣿⣿⡿⠛⠉⠉⠉⠉⠁");
            System.out.println("⠘⠛⠛⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠛⠛⠃⠀⠀⠀⠀⠀⠀⠀");
        }
        else{
           System.out.println(" ⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣶⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
           System.out.println(" ⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣦⣄⣀⡀⣠⣾⡇⠀⠀⠀⠀");
           System.out.println(" ⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀");
           System.out.println(" ⠀⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⢿⣿⣿⡇⠀⠀⠀⠀");
           System.out.println(" ⣶⣿⣦⣜⣿⣿⣿⡟⠻⣿⣿⣿⣿⣿⣿⣿⡿⢿⡏⣴⣺⣦⣙⣿⣷⣄⠀⠀⠀");
           System.out.println(" ⣯⡇⣻⣿⣿⣿⣿⣷⣾⣿⣬⣥⣭⣽⣿⣿⣧⣼⡇⣯⣇⣹⣿⣿⣿⣿⣧⠀⠀");
           System.out.println(" ⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠸⣿⣿⣿⣿⣿⣿⣿⣷⠀");
        }
    }

    //This is really scuffed but it works (okayish)
    class KeyEventHandler extends JFrame implements KeyListener{
        TerminalGame game;
        public KeyEventHandler( TerminalGame game ){
            this.game = game;
            add( new JLabel("CLICK ON THIS TO REGISTER INPUT") );
            setSize( 300, 300);
            setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            addKeyListener( this );
            setVisible( true );
        }
        public void keyTyped(KeyEvent e){
        }
        public void keyPressed(KeyEvent e){
        }
        public void keyReleased(KeyEvent e){
            game.registerEvent( e );
        }
    }
}