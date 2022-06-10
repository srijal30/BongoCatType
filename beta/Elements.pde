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
  color fil;
  Label(float x, float y, float size, String text){
    super(x,y);
    this.size = size; this.text = text;
    fil = TEXTCOLOR;
  }
  Label( float x, float y, float size, String text, color fil){
    super(x,y);
    this.size = size; this.text = text; this.fil = fil;
  }
  void draw(){
    super.draw();
    textAlign(CENTER);
    fill(fil);
    textSize(size);
    text( text, x, y);
  }
  String getText(){
    return text;
  }
  void setText( String other ){
    text = other;
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


//does not matter the position
class ScrollLabelList extends Element{
  LinkedList<String> labels;
  int head = 0;
  public ScrollLabelList( float x, float y, LinkedList<String> labels ){
    super(x,y);
    head = 0;
    this.labels = labels;
  }
  //detect arrow keys
  void process(){
    if( keyPressed ){
      if( keyCode == UP ){
        scrollUp();
      }
      else if( keyCode == DOWN){
        scrollDown();
      }
    }
  }
  void scrollUp(){
    head -= 1;
    if( head < 0 ) head = 0;
  }
  void scrollDown(){
    if( head >= labels.size()-1 ) return;
    head +=1;
  }
  //renders only the top X labels
  void draw(){
    //setup vars
    textAlign(CENTER);
    textSize(20);
    int renderAmount = 18;
    float incrementY = textAscent();
    float currentY = super.y;
    fill(#000000);
    for( int i = head; i < Math.min(head+renderAmount, labels.size() ); i++ ){
      text( labels.get(i), super.x, currentY );
      currentY += incrementY;
    }
  }
}