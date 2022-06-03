import java.util.Scanner;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

//create methods to get valid input (INPUT HANDLERS)
public class TerminalGame {
    private KeyEventHandler keys;
    private GameManager manager;
    private Scanner input; 
    private boolean started = false;
    private String userText;
    //Start a Game on Construction 
    public TerminalGame(){
        input = new Scanner( System.in );
        manager = new GameManager();
        keys = new KeyEventHandler(this);
        setup();
        playGame();
    }
    //sets a game up (by getting difficulty and wordCount)
    public void setup(){
        userText = "";
        System.out.print("Select difficulty from 0 to 3: ");
        WordGenerator.difficulty = input.nextInt() ;
        System.out.print("Select amount of words you would like the test to be: ");
        manager.newGame( input.nextInt() );
    }
    //plays game from start to finish
    public void playGame(){
        started = true;
        while( true ){
            clear();
            System.out.println( "STATS: " + manager.getRawWPM() + "\t" + manager.getRealWPM() + "\t" + manager.getAccuracy() + "\n" );
            System.out.println( manager.getText() + "\n" );
            System.out.println( userText );
            Ansi.wait( 1000 );
        }
    }
    public void registerEvent( KeyEvent e ){
        if( !started ) return;
        //if backspace
        if( e.getKeyCode() == 8 ){
            if( manager.removeCharacter() ){
                userText = userText.substring(0, userText.length()-2);
            };
        }
        else{
            boolean correct = manager.pushCharacter( e.getKeyChar() );
            if( correct ){
                userText += Ansi.color(Ansi.GREEN,Ansi.background(Ansi.BLACK))+e.getKeyChar();
            }
            else{
                userText += Ansi.color(Ansi.RED,Ansi.background(Ansi.BLACK))+e.getKeyChar();
            }
            manager.update();
        }
    }
    private static void clear()
    // Inspired from TerminallyIll.java
    // Clears the screen and makes the cursor move to the top left of the terminal
    {
      System.out.println(Ansi.CLEAR_SCREEN + Ansi.RESET);
      System.out.println("\033[" + 1 + ";" + 1 + "H");
    }
}
//This is really scuffed
class KeyEventHandler extends JFrame implements KeyListener{
    TerminalGame game;
    public KeyEventHandler( TerminalGame game ){
        this.game = game;
        add( new JLabel(" CLICK ON THIS TO REGISTER INPUT") );
        setSize( 300, 300);
        setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
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
