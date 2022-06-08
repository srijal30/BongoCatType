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
    
    //set the current page to home
    currentPage = new Home();
    currentPage.setup();

    //create settings button
    settings = new Button(width/2, height/2, 20, "*", 30, 30, new Settings());
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
}

//for theme switchings
color STANDARD;
color HOVER;
color OUTLINE;
color TEXTCOLOR;
boolean darkMode = false;
void toggleTheme(){
  if( darkMode ){
  }
  else{
    STANDARD = #ffffff;
    HOVER = #a8a8a8;
    OUTLINE = #000000;
    TEXTCOLOR = #000000;
  }
}
