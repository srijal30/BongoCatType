import java.util.ArrayList;

class Page {
  ArrayList<Element> elements;
  Page(){
    elements = new ArrayList<Element>();
  }
  void setup(){}
  void process(){
    for( Element e: elements ) e.process();
  }
  void draw(){
    for( Element e: elements ) e.draw();
  }
}

class Home extends Page {
  void setup(){
    //BONGOCAT ANIMATION
    elements.add(
      new Animation(width/2-530, height/2-125,
        new PImage[]{ loadImage("handsup.png"), loadImage("handsdown.png") }
      )
    );
    //TITLE LABEL
    elements.add( 
      new Label( width/2, height/2-225, 100, "BongoCatType" )
    );
    //ABOUT PAGE BUTTON
    elements.add(
      new Button( width/2, height/2+25, 15, "About", 100, 30, new About() )
    );
    //GAME PAGE BUTTON
    elements.add(
      new Button( width/2, height/2-75, 15, "Play", 100, 30, new Game() )
    );
    //LEADERBOARD PAGE BUTTON
    elements.add(
      new Button( width/2, height/2-25, 15, "Leaderboard", 100, 30, new Leaderboard() )
    );
  }
}

class Game extends Page {
  GameManager manager;
  Game(){
    super();
    manager = new GameManager();  
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
    //INPUT BOX
    elements.add(
      new InputBox( width/2, height/2, 15, "Input a Value", 100, 30)
    );
  }
}

class About extends Page {
  void setup(){
    //ABOUT PAGE HEADER
    elements.add( 
      new Label( width/2, height/2-250, 75, "About Page" )
    );
    //ABOUT PAGE INFORMATION
    elements.add(
      new TextBox( width/2, height/2-150, 25, "BongoCatType took heavy inspiration from another program\n called MonkeyType. Although MonkeyType is a very cool app, we feel like it needed some improvements. Thats why we spent the last few years developing BongoCatType, the best typing\n experience the world has seen so far.\n - Faiyaz, Salaj, Alif", 400 )
    );
    //HOME PAGE BUTTON
    elements.add(
      new Button( width/2, height/2+125, 15, "Home", 100, 30, new Home() )
    );

  }
}

class Settings extends Page{
  void setup(){
    elements.add(
      new Label(width/2, 100, 100, "Settings")
    );
  }
}

class Leaderboard extends Page{
  void setup(){
    //LEADERBOARD LABEL
    elements.add(
      new Label(width/2, 150, 100, "Leaderboard")
    );
    //HOME PAGE BUTTON
    elements.add(
      new Button( width/2, height/2+250, 15, "Home", 100, 30, new Home() )
    );
  }
}
