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
     //GAMEFONT
    elements.add(
      new Animation(width/2-430, height/2-380,
        new PImage[]{ loadImage("font.png") }
      )
    );

    //ABOUT PAGE BUTTON
    elements.add(
      new Button( width/2, height/2+25, 15, "About", 100, 30, new About() )
    );
    //GAME PAGE BUTTON
    elements.add(
      new Button( width/2, height/2-75, 15, "Play", 100, 30, new GameSetup() )
    );
    //LEADERBOARD PAGE BUTTON
    elements.add(
      new Button( width/2, height/2-25, 15, "Leaderboard", 100, 30, new Leaderboard() )
    );
    //RESULTS PAGE BUTTON
    elements.add(
      new Button( width/2, height/2-125, 15, "Results", 100, 30, new Results() )
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
      new TextBox( width/2, height/2-150, 25, "BongoCatType took heavy inspiration from another program\n called MonkeyType. Although MonkeyType is a very cool app, we feel like it needed some improvements. Thats why we spent the last few years developing BongoCatType, the best typing\n experience the world has seen so far.\n\n - Faiyaz, Salaj, Alif", 500 )
    );
    //HOME PAGE BUTTON
    elements.add(
      new Button( width/2, height/2+250, 15, "Home", 100, 30, new Home() )
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

class Results extends Page{
  void setup(){
    //HOME PAGE BUTTON
    elements.add(
      new Button( width/2, height/2+250, 55, "Bongo!", 190, 100, new Home() )
    );
  }
}


class Test extends Page{

  void setup(){

    SelectButton[] test = {
      new SelectButton(width/2, height/2, 15, "HAPPY", 100, 30),
      new SelectButton(width/2, height/2+30, 15, "DAYS", 100, 30),
      new SelectButton(width/2, height/2+60, 15, "BE COMING", 100, 30),
      new SelectButton(width/2, height/2+90, 15, "SIR", 100, 30)
    };

    elements.add( new SelectButtonGroup( test ) );

  }

}