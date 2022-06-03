import java.util.Scanner;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.LinkedList;

//create methods to get valid input (INPUT HANDLERS)
public class TerminalGame {
    private KeyEventHandler keys;
    private GameManager manager;
    private Scanner input; 
    private boolean started = false;
    private LinkedList<Boolean> colors;
    //Start a Game on Construction 
    public TerminalGame(){
        input = new Scanner( System.in );
        manager = new GameManager();
        keys = new KeyEventHandler(this);
        setup();
        //start the game (maybe make seperate method)
        started = true;
        updateScreen();
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
    //update the screen aka the frame
    public void updateScreen(){
        System.out.println(Ansi.CLEAR_SCREEN + Ansi.RESET);
        System.out.println("\033[" + 1 + ";" + 1 + "H");
        System.out.println( "STATS: " + manager.getRawWPM() + "\t" + manager.getRealWPM() + "\t" + manager.getAccuracy() + "\n" );
        //print out the colors
        String tmp = manager.getInput();
        for( int i = 0; i < tmp.length(); i++ ){
            //green
            if( colors.get(i) ){
                System.out.print( Ansi.color(Ansi.GREEN,Ansi.background(Ansi.BLACK))+tmp.charAt(i));
            }
            //red
            else{
                System.out.print( Ansi.color(Ansi.RED,Ansi.background(Ansi.BLACK))+tmp.charAt(i));
            }
        }
        System.out.println( Ansi.RESET + manager.getText( tmp.length() ) + "\n" );
        System.out.println( Ansi.RESET );
    }
    //register keyboard input
    public void registerEvent( KeyEvent e ){
        if( !started ) return;
        //if backspace
        if( e.getKeyCode() == 8 ){
            if( manager.removeCharacter() ) colors.removeLast();
        }
        //if any other character
        else{
            colors.add( manager.pushCharacter( e.getKeyChar() ) );
        }
        manager.update();
        updateScreen();
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
