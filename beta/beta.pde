Page currentPage;
Button settings;

void setup() {
    //load the words
    String[] words = loadStrings("default.txt");
    for ( String word : words ) WordGenerator.addWord( word );
    //load the required assets
    loadFiles();
    //window setup
    size(1000, 800);
    surface.setTitle("BongoCatType");
    surface.setResizable(true);
    frameRate(60);
    surface.setIcon(  switchIcon );
    //set the current page to home
    currentPage = new Home();
    currentPage.setup();
    textFont(normal);
}

void draw(){
  // background stuff
  background(255,192,203);

  //current page stuff
  //currentPage.setup(); //to detect for resize
  currentPage.process();
  currentPage.draw();
}

//load all the files
void loadFiles(){
  title = loadImage("assets/title.png");
  switchIcon = loadImage("assets/switch.png");
  happy = new PImage[]{ loadImage("assets/happy1.png"),loadImage("assets/happy2.png") } ;
  sad = new PImage[]{ loadImage("assets/sad1.png"),loadImage("assets/sad2.png") } ;
  bongo = new PImage[]{ loadImage("assets/normal1.png"),loadImage("assets/normal2.png") } ;
  
  typing = createFont("assets/typing.ttf", 12);
  normal = createFont("assets/normal.ttf", 12);
}

void keyReleased() {
  currentPage.processKeyPress();  
}

//FOR LOOKING GOOD UI
//IMAGE STUFF
PImage switchIcon ;
PImage title ;
PImage[] happy;
PImage[] sad;
PImage[] bongo;

//FONT STUFF
PFont typing;
PFont normal;

//for theme switchings
color STANDARD = #ffffff;
color HOVER = #a8a8a8;
color SELECTED = #90EE90;
color OUTLINE = #000000;
color TEXTCOLOR = #000000;
color GRAY = #808080;
color RED = #FF0000;
color BLACK = #000000;
boolean darkMode = false;

void toggleTheme(){
}

