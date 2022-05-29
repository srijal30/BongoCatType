import java.util.ArrayList;

interface Page {

  //to process keypresses and clicks
  void process( boolean clickEvent );
  
  //to draw the page
  void draw();
  
}


class Home implements Page {
  Button[] buttons;
  Label[] labels;
  Home(){
    buttons = new Button[]{
      //button to go to Game Page
      new Button( width/2, height/2, 100, 30, "About", ABOUT ),
      //button for About Page
    };  
    labels = new Label[]{
    };
  }
  void process( boolean clickEvent ){
    for( Button b: buttons ) b.process( clickEvent ); // false cuz no click event
    for( Label l: labels ) l.process();
  }
  void draw(){
    //draw the elements
    for( Button b: buttons ) b.draw();
    for( Label l: labels ) l.draw();
  }
}


class Game implements Page {
  Button[] buttons;
  Label[] labels;
  Game(){
    buttons = new Button[]{
      //button to go to Home Page
      new Button( width/2, height/2+150, 100, 50, "Go Home", HOME ),
    };  
    labels = new Label[]{
    };
  }
  void process( boolean clickEvent ){
    for( Button b: buttons ) b.process( clickEvent ); // false cuz no click event
    for( Label l: labels ) l.process();
  }
  void draw(){
    //draw the elements
    for( Button b: buttons ) b.draw();
    for( Label l: labels ) l.draw();
  }
}

class About implements Page {
  Button[] buttons;
  Label[] labels;
  About(){
    buttons = new Button[]{
      //button to go to Home Page
      new Button( width/2, height/2, 100, 50, "Go Home", HOME ),
    };  
    labels = new Label[]{
      new Label( width/2, height/2-80, 30, "About Page"),
    };
  }
  void process( boolean clickEvent ){
    for( Button b: buttons ) b.process( clickEvent ); // false cuz no click event
    for( Label l: labels ) l.process();
  }
  void draw(){
    //draw the elements
    for( Button b: buttons ) b.draw();
    for( Label l: labels ) l.draw();
  }
}
