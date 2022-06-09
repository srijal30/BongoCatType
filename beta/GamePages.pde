import java.util.LinkedList;
//FOR GAME RELATED PAGES

//get all the info neccassary for a Game
class GameSetup extends Page{
    InputBox nameField;
    SelectButtonGroup diffChoice;
    SelectButtonGroup wordCountChoice;
    SelectButton playButton;
    void setup(){
        //Whats Your Name?
        nameField = new InputBox( width/2, height/2-270, 15, "Your Name", 100, 30);
        elements.add( new Label( width/2, height/2-300, 20, "Input Your Name:" ) );
        elements.add(  nameField  );
        //Choose A Difficulty?
        elements.add( new Label( width/2, height/2-130, 20, "Choose A Difficulty:" ) );
        SelectButton[] diffs = new SelectButton[]{
            new SelectButton( width/2-225, height/2-100, 15, "Easy", 100, 30),
            new SelectButton( width/2-75, height/2-100, 15, "Medium", 100, 30),
            new SelectButton( width/2+75, height/2-100, 15, "Hard", 100, 30),
            new SelectButton( width/2+225, height/2-100, 15, "Extreme", 100, 30)
        };
        diffChoice = new SelectButtonGroup( diffs );
        elements.add( diffChoice );
        //Choose Word Count?
        elements.add( new Label( width/2, height/2+40, 20, "Choose A WordCount:" ) );
        SelectButton[] wiffs = new SelectButton[]{
            new SelectButton( width/2-225, height/2+70, 15, "10", 100, 30),
            new SelectButton( width/2-75, height/2+70, 15, "25", 100, 30),
            new SelectButton( width/2+75, height/2+70, 15, "50", 100, 30),
            new SelectButton( width/2+225, height/2+70, 15, "100", 100, 30)
        };
        wordCountChoice = new SelectButtonGroup( wiffs );
        elements.add( wordCountChoice );
        //Play Game Button
        playButton = new SelectButton( width/2, height/2+270, 25, "Play Game!", 150, 50);
        elements.add( playButton );
        //RETURN HOME
        elements.add(
            new Button( width/2, height/2+325, 15, "Exit", 100, 30, new Home() )
        );
    }
    //updates values accordingly
    void process(){
        //check if user pressed playButton
        if( playButton.selected ){
            playButton.selected = false;
            //checks to see if the valid inputs are selected
            if( nameField.getText().equals("Your Name") || nameField.getText().equals("") ) println("PLEASE ENTER A VALID NAME!");
            else if( diffChoice.selected == null ) println("PLEASE CHOOSE A DIFFICULTY!");
            else if( wordCountChoice.selected == null ) println("PLEASE CHOOSE A WORD COUNT!");
            //if this is called, then that means you can go to next page
            else{
                int wordCount = Integer.parseInt( wordCountChoice.selected.text );
                int difficulty;
                switch( diffChoice.selected.getText() ){
                  case "Easy": 
                  difficulty = 0;
                  break;
                  case "Medium": 
                  difficulty = 1;
                  break;
                  case "Hard": 
                  difficulty = 2;
                  break;
                  case "Extreme": 
                  difficulty = 3;
                  break;
                  default:
                  difficulty = 0;
                  break;
                }
                currentPage = new Game( nameField.getText(), difficulty, wordCount );
                currentPage.setup();
            }
        }
        super.process();
    }
}

//page where you actually play the Game
class Game extends Page {
  GameManager manager;
  Label acc;
  Label raw;
  Label real;
  boolean started;
  LinkedList<Boolean> colors;
  int renderStart; // where we start rendering text <it incrememnets by row size>
  int increment = 10;

  Game(String name, int diff, int wC){
    super();
    WordGenerator.difficulty = diff;
    manager = new GameManager();
    //setting up the game
    colors = new LinkedList<Boolean>();
    started = false;
    manager.newGame( wC );
    int renderStart = 0;
    println(diff);
  }

  void setup(){
    //TITLE
    elements.add(
      new Label( width/2, height/2-225, 100, "Game")
    );
    //RETURN HOME
    elements.add(
      new Button( width/2, height/2+300, 15, "Exit", 100, 30, new Home() )
    );
    //LIVE STAT LABELS
    real = new Label( width/2 - 150 , height/2+240, 20, "0.0" );
    acc = new Label( width/2 , height/2+240, 20, "0.0" );
    raw = new Label( width/2 + 150 , height/2+240, 20, "0.0" );
    elements.add( real );
    elements.add( acc );
    elements.add( raw );
  }

  void process(){
    real.text = String.format("%3.2f", manager.getRealWPM()) ;
    acc.text =  String.format("%3.2f%%",manager.getAccuracy()*100) ;
    raw.text =  String.format("%3.2f", manager.getRawWPM() ) ;
    //check if key is pressed
    if( keyPressed ){
        processKeyPress();
        manager.update();
    }
    super.process();
  }

  void draw(){
    super.draw();
    int startX = 100;
    int startY = 100;

    //draw the text
    textFont(courier);
    String userStuff = manager.getInput();
    for( int i = 0; i < userStuff.length(); i++ ){
      if( colors.get(i) ) fill(BLACK);
      else fill(RED);
      text( userStuff.charAt(i), startX, startY );
      startX += increment;
      if( startX > 400 ){
        startX = 100;
        startY += increment;
      }
    }
    //continue filling from where we left off
    String gameStuff = manager.getText( userStuff.length() );
    fill(GRAY);
    for( int i = 0; i < gameStuff.length(); i++ ){
      text( gameStuff.charAt(i), startX, startY );
      startX += increment;
      if( startX > 400 ){
        startX = 100;
        startY += increment;
      }        
    }
    textFont(normal);
  }

  //processing a keypress
  void processKeyPress(){
      //start the timer if it hasnt been started alr
      if( !started ) { started = true; manager.startTimer(); }
      //key detection
      if( key == BACKSPACE ){
        if( manager.removeCharacter() ) colors.removeLast();
      }
      else if ( key != SHIFT ){
        colors.add( manager.pushCharacter( key) );
      }
      keyPressed = false;
  }

  void endGame(){

  }
}

//page where you get the results for your game
class GameResult extends Page {

}

