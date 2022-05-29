Page HOME;
Page ABOUT;
Page GAME;

Page currentPage;

void newPages(){
}

void setup() {
    //load the words
    String[] words = loadStrings("default.txt");
    for ( String word : words ) WordGenerator.addWord( word );
    size(600, 600);
    
    //other stuff
    surface.setTitle("BongoCatType");
    surface.setResizable(true);
    
    //create the pages
    HOME = new Home();
    ABOUT = new About();
    GAME = new Game();
    //set the current page to home
    currentPage = HOME;
    
}

void mouseClicked(){
  currentPage.process(true);
}

void draw(){
  //background stuff
  background(255,192,203);
  
  //current page stuff
  currentPage.process(false); //no click event
  currentPage.draw();
}
