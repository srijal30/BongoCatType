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
      new Animation(width/2-290, height/2+10,
        new PImage[]{ loadImage("handsup.png"), loadImage("handsdown.png") }
      )
    );
    //TITLE LABEL
    elements.add( 
      new Label( width/2, height/2-225, 100, "BongoCatType" )
    );
    //ABOUT PAGE BUTTON
    elements.add(
      new Button( width/2, height/2-25, 15, "About", 100, 30, new About() )
    );
    //GAME PAGE BUTTON
    elements.add(
      new Button( width/2, height/2-75, 15, "Play", 100, 30, new Game() )
    );
  }
}

class Game extends Page {
  void setup(){
    


  }
}

class About extends Page {
  void setup(){
    
    //ABOUT PAGE HEADER
    elements.add( 
      new Label( width/2, height/2-200, 100, "ABOUT" )
    );

    //HOME PAGE BUTTON
    elements.add(
      new Button( width/2, height/2-25, 15, "Home", 100, 30, new Home() )
    );


  }
}
