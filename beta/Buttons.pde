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
    textSize(18);
    boolean onButton = mouseX > x-w/2 && mouseX < x+w/2 && mouseY > (y-textAscent()/3)-h/2 && mouseY < (y-textAscent()/3)+h/2;
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
    textSize(18);
    rectMode(CENTER);
    //make outline first
    fill(OUTLINE);
    rect( x, y-textAscent()/3, w, h);
    //then make inside
    fill(current);
    rect( x, y-textAscent()/3, w-4, h-4);
    //render the label
    super.draw();
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
  //return the text
  String getText(){
    return super.text;
  }
}

//button that lights up when selected
class SelectButton extends Button{
    boolean selected;
    SelectButton(float x, float y, float size, String text, float w, float h){
        super( x, y, size, text, w, h, null );
        selected = false;
    }
    void process(){
        super.process();
        if( selected ) super.current = SELECTED;
    }
    //what happens when clicked
    void onClick(){ 
        selected = !selected;
        mousePressed = false;
    }
}

//maintains that only Button is selected
class SelectButtonGroup extends Element{
    SelectButton selected;
    SelectButton[] buttonList;
    //location of this element does not matter
    SelectButtonGroup( SelectButton[] buttons ){
        super(0, 0);
        buttonList = buttons;
    }
    void process(){
        //latest button detected to be true will be selected button
        for( SelectButton b : buttonList ){
            b.process();
            //only changes selected when detects a change in selected button
            if ( b.selected && b != selected){
                selected = b;
                deselectOthers();
            } 
        }
    }
    void deselectOthers(){
        for( SelectButton b : buttonList){
            if( b != selected ) b.selected = false;
        }
    }
    void draw(){
        for( Button b: buttonList ) b.draw();
    }
}

