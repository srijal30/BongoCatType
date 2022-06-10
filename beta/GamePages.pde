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
        nameField = new InputBox( width/2, height/2-270, 18, "Your Name", 100, 30);
        elements.add( new Label( width/2, height/2-300, 20, "Input Your Name:" ) );
        elements.add(  nameField  );
        //Choose A Difficulty?
        elements.add( new Label( width/2, height/2-130, 20, "Choose A Difficulty:" ) );
        SelectButton[] diffs = new SelectButton[]{
            new SelectButton( width/2-225, height/2-100, 18, "Easy", 100, 30),
            new SelectButton( width/2-75, height/2-100, 18, "Medium", 100, 30),
            new SelectButton( width/2+75, height/2-100, 18, "Hard", 100, 30),
            new SelectButton( width/2+225, height/2-100, 18, "Extreme", 100, 30)
        };
        diffChoice = new SelectButtonGroup( diffs );
        elements.add( diffChoice );
        //Choose Word Count?
        elements.add( new Label( width/2, height/2+40, 20, "Choose A WordCount:" ) );
        SelectButton[] wiffs = new SelectButton[]{
            new SelectButton( width/2-225, height/2+70, 18, "10", 100, 30),
            new SelectButton( width/2-75, height/2+70, 18, "25", 100, 30),
            new SelectButton( width/2+75, height/2+70, 18, "50", 100, 30),
            new SelectButton( width/2+225, height/2+70, 18, "100", 100, 30)
        };
        wordCountChoice = new SelectButtonGroup( wiffs );
        elements.add( wordCountChoice );
        //Play Game Button
        playButton = new SelectButton( width/2, height/2+270, 25, "Play Game!", 150, 50);
        elements.add( playButton );
        //RETURN HOME
        elements.add(
            new Button( width/2, height/2+325, 18, "Exit", 100, 30, new Home() )
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
  String name;

  //for displaying properly
  LinkedList<Boolean> colors;
  int renderStart; // where we start rendering text <it incrememnets by row size>

  Game(String name, int diff, int wC){
    super();
    WordGenerator.difficulty = diff;
    manager = new GameManager();
    this.name = name;
    //setting up the game
    colors = new LinkedList<Boolean>();
    started = false;
    manager.newGame( wC );
    int renderStart = 0;
  }

  void setup(){
    //TITLE
    elements.add(
      new Label( width/2, height/2-225, 100, "Game")
    );
    //RETURN HOME
    elements.add(
      new Button( width/2, height/2+300, 18, "Exit", 100, 30, new Home() )
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
    super.process();
  }

  void draw(){
    super.draw();

    //draw the text
    int maxY = 533;
    int maxX = 667+75;
    int startX = 333-75;
    int startY = 267;

    int currentX = startX;
    int currentY = startY;
    textFont(typing);
    textSize(18);
    float incrementX = textWidth("M");
    float incrementY = textAscent()+10;

    String userStuff = manager.getInput();
    for( int i = 0; i < userStuff.length(); i++ ){
      if( i < renderStart ){
        continue;
      }
      if( currentY > maxY ){
        renderStart = i;
        continue;
      }
      if( colors.get(i) ) fill(BLACK);
      else fill(RED);
      //check if space
      if( userStuff.charAt(i) == ' ' && !colors.get(i) ) text( "_", currentX, currentY );
      else text( userStuff.charAt(i), currentX, currentY );
      currentX += incrementX;
      if( currentX > maxX ){
        currentX = startX;
        currentY += incrementY;
      }
    }
    //continue filling from where we left off
    String gameStuff = manager.getText( userStuff.length() );
    fill(GRAY);
    for( int i = 0; i < gameStuff.length(); i++ ){
      if( currentY > maxY ){
        continue;
      }
      text( gameStuff.charAt(i), currentX, currentY );
      currentX += incrementX;
      if( currentX > maxX ){
        currentX = startX;
        currentY += incrementY;
      }        
    }
    textFont(normal);
  }

  //processing a keypress
  //there is an issue with this
  void processKeyPress(){
      //start the timer if it hasnt been started alr
      if( !started ) { started = true; manager.startTimer(); }
      //key detection
      if( key == BACKSPACE ){
        if( manager.removeCharacter() ) colors.removeLast();
      }
      else{
        colors.add(manager.pushCharacter(key) );
      }
      if( manager.update() ) endGame();
  }

  void endGame(){
    //push to leaderboard
    Leaderboard.addEntry(name, manager.getRealWPM(), manager.getAccuracy(), manager.getRawWPM() );
    //take user to next page
    currentPage = new Result( manager.getRealWPM(), manager.getAccuracy(), manager.getRawWPM() );
    currentPage.setup();
  }
}

//page where you get the results for your game
class Result extends Page {
  String raw;
  String real;
  String acc;
  Result( double real, double acc, double raw ){
    this.real = String.format("%3.2f", real ) ;
    this.acc =  String.format("%3.2f%%",acc*100) ;
    this.raw =  String.format("%3.2f", raw ) ;
    if( (real) < 40 ){
      elements.add( new Label(width/2, height/2-300, 25, "You did okay...\nbetter luck next time!") );
      elements.add( new Animation(width/2-250, height/2-200, sad) );
    }
    else if( (real) < 80 ){
      elements.add( new Label(width/2, height/2-300, 25, "You are average!\nTry again to increase your score.") );
      elements.add( new Animation(width/2-250, height/2-200, bongo) );
    }
    else{
      elements.add( new Label(width/2, height/2-300, 25, "You are pretty good at typing!\n Do you touch grass?") );
      elements.add( new Animation(width/2-250, height/2-200, happy) );
    }
  }
  void setup(){
    //HOME PAGE BUTTON
    elements.add(
      new Button( width/2, height/2+250, 18, "Bongo!", 100, 30, new Home() )
    );

    elements.add( new Label( width/2, height/2+320, 30, "RealWPM: " + real + "     Accuracy:" + acc + "     RawWPM: " + raw) );

  }

}

