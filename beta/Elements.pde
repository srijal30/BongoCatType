import java.util.LinkedList;

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
  String getText(){
    return text;
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

//needs reworking
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

//THIS WILL BE USEFUL
//textAscent(); <-- returns height of words
//textWidth(); <-- returns width of words
