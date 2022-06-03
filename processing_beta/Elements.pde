//defaults
color STANDARD = #ffffff;
color HOVER = #a8a8a8;
color OUTLINE = #000000;
color TEXTCOLOR = #000000;

class Element{
  float x, y;
  Element(float x, float y){this.x = x; this.y = y;}
  void draw(){};
  void process(){};
}

//for text elements
class Label extends Element{
  String text;
  float size;
  Label(float x, float y, float size, String text){
    super(x,y);
    this.size = size; this.text = text;
  }
  void draw(){
    super.draw();
    textAlign(CENTER);
    fill(TEXTCOLOR);
    textSize(size);
    text( text, x, y);
  }
}

//for interactable buttons that take you to other pages
class Button extends Label{
  color current;
  float w, h;
  Page nextPage;
  Button(float x, float y, float size, String text, float w, float h, Page next){
    super(x, y, size, text);
    this.w = w; this.h = h;
    this.text = text; nextPage = next;
    current = STANDARD;
  }
  //check mouse position and if mouse was clicked, act accordingly
  void process(){
    super.process();
    boolean onButton = mouseX > x-w/2 && mouseX < x+w/2 && mouseY > y-h/2 && mouseY < y+h/2;
    //color is standard
    if( !onButton ) current = STANDARD;
    //if there is nextPage, go to it. Else print no connection
    else if( mousePressed ) onClick();
    //mouse is hovering over button
    else current = HOVER;
  }
  //what happens when clicked
  void onClick(){ 
    if( nextPage == null ) println("NO PAGE CONNECTED"); 
    else{
      currentPage = nextPage; 
      currentPage.setup(); 
      mousePressed = false;
    }
  }
  void draw(){
    rectMode(CENTER);
    //make outline first
    fill(OUTLINE);
    rect( x, y, w, h);
    //then make inside
    fill(current);
    rect( x, y, w-4, h-4);
    //render the label
    super.draw();
  }
}

//for animations
class Animation extends Element{
  PImage[] frames;
  int currentFrame;
  Animation( float x, float y, PImage[] frames ){
    super(x, y);
    this.frames = frames;
    currentFrame = 0;
  }
  void process(){
    super.process();
    currentFrame = (frameCount%60)/(60/frames.length);
  }
  void draw(){
    super.draw();
    image( frames[currentFrame], x, y );
  }
}

//for textboxes with a fixed width
class TextBox extends Label{
  int width;
  TextBox( float x, float y, float size, String text, int width){
    super( x, y, size, text);
    //add newlines to the text according to width guidlines
    String result = "";
    String current = "";
    for( char c : text.toCharArray() ){
      if( textWidth(current) > width ){result += current + "\n"; current = "";}
      current += c;
    }
    result += current;
    super.text = result;
  }
}

//for user input
class InputBox extends Button{
  boolean selected;
  InputBox(float x, float y, float size, String text, float w, float h){
    super(x, y, size, text, w, h, null);
    selected = false;
  }
  //process
  void process(){
    super.process();
    if( selected ){
      if( keyPressed ){
        if( key == ENTER ) selected = false;
        else if (key == BACKSPACE && !super.text.equals("")) super.text = super.text.substring(0, super.text.length()-1 );
        else if ( Character.isLetterOrDigit(key) ) super.text += key;
        keyPressed = false;
      }
    } 
  }
  //override the onClick
  void onClick(){
    super.text = "";
    selected = true;
  }
}