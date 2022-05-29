//defaults
color STANDARD = #ffffff;
color HOVER = #a8a8a8;
color OUTLINE = #000000;
color TEXTCOLOR = #000000;

//for interactable buttons that take you to ther pages
//maybe use a label inside a button?
class Button{
  color current;
  float x, y, w, h;
  String text;
  Page nextPage;

  Button(float x, float y, float w, float h, String text, Page next){
    //based on constructor
    this.x = x; this.y = y; this.w = w; this.h = h;
    this.text = text; nextPage = next;
    //other stuff
    current = STANDARD;
  }
  
  //check mouse position and if mouse was clicked, act accordingly
  void process( boolean clickEvent ){
    boolean onButton = mouseX > x-w/2 && mouseX < x+w/2 && mouseY > y-h/2 && mouseY < y+h/2;
    if( !onButton ){
       current = STANDARD;
    }
    //you can assume from here on out, that mouse is over button
    else if( clickEvent ){
      if( nextPage == null ) { println("NO PAGE CONNECTED"); return; }
      currentPage = nextPage;
    }
    else{
      current = HOVER;
    }
  }
  void draw(){
    textAlign(CENTER);
    rectMode(CENTER);
    //make outline first
    fill(OUTLINE);
    rect( x, y, w, h);
    //then make inside
    fill(current);
    rect( x, y, w-4, h-4);
    //render the text
    fill(TEXTCOLOR);
    textSize(15);
    text( text, x, y+6);
   }
}


//for text elements
class Label{
  String text;
  float x, y, size;
  Label(float x, float y, float size, String text){
    this.x = x; this.y = y; this.size = size; this.text = text;
  }
  void process(){
  }
  void draw(){
    fill(TEXTCOLOR);
    textAlign(CENTER);
    textSize(size);
    text( text, x, y);
  }
}
