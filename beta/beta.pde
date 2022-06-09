Page currentPage;
Button settings;

void setup() {
    //load the words
    String[] words = loadStrings("default.txt");
    for ( String word : words ) WordGenerator.addWord( word );
    size(1000, 800);
    
    //other stuff
    surface.setTitle("BongoCatType");
    surface.setResizable(true);
    frameRate(60);
    //surface.setIcon(  loadImage("handsdown.png") );
    toggleTheme();
    
    //FOR TESTING
    currentPage = new Test();


    //set the current page to home
    currentPage = new Home();
    currentPage.setup();

    courier = createFont( "Courier New", 12);
    normal = createFont("Lucida Sans", 12);
    textFont(normal);
}

void draw(){
  // background stuff
  background(255,192,203);

  //current page stuff
  //currentPage.setup(); //to detect for resize
  currentPage.process();
  currentPage.draw();

  //settings button
  //settings.process();
  //settings.draw();
  println( mouseX + " " + mouseY);
}

//for theme switchings
color STANDARD;
color HOVER;
color SELECTED;
color OUTLINE;
color TEXTCOLOR;
color GRAY = #808080;
color RED = #FF0000;
color BLACK = #000000;
PFont normal;
PFont courier;
boolean darkMode = false;
void toggleTheme(){
  if( darkMode ){
  }
  else{
    STANDARD = #ffffff;
    HOVER = #a8a8a8;
    SELECTED = #90EE90;
    OUTLINE = #000000;
    TEXTCOLOR = #000000;
  }
}

