import java.util.ArrayList;
import java.util.LinkedList;

class Page {
  ArrayList<Element> elements;
  Page(){
    elements = new ArrayList<Element>();
  }
  void setup(){}
  void process(){
    for( Element e: elements ) e.process();
  }
  void draw(){
    for( Element e: elements ) e.draw();
  }
  void processKeyPress(){
  }
}

class Home extends Page {
  void setup(){
    //BONGOCAT ANIMATION
    elements.add(
      new Animation(width/2-250, height/2+60, happy)
    );
    //GAMEFONT
    elements.add(
      new Animation(width/2-430, height/2-380,
        new PImage[]{ title }
      )
    );
    //ABOUT PAGE BUTTON
    elements.add(
      new Button( width/2, height/2+25, 18, "About", 100, 30, new About() )
    );
    //GAME PAGE BUTTON
    elements.add(
      new Button( width/2, height/2-75, 18, "Play", 100, 30, new GameSetup() )
    );
    //LEADERBOARD PAGE BUTTON
    elements.add(
      new Button( width/2, height/2-25, 18, "Leaderboard", 100, 30, new LeaderboardPage() )
    );
  }
}

class About extends Page {
  void setup(){
    //ABOUT PAGE HEADER
    elements.add( 
      new Label( width/2, height/2-250, 75, "About Page" )
    );
    //ABOUT PAGE INFORMATION
    elements.add(
      new TextBox( width/2, height/2-160, 25, "BongoCatType took heavy inspiration from another program\n called MonkeyType. Although MonkeyType is a very cool app, we feel like it needed some improvements. Thats why we spent the last few years developing BongoCatType, the best typing\n experience the world has seen so far.\n\n - Faiyaz, Salaj, Alif", 500 )
    );
    //HOME PAGE BUTTON
    elements.add(
      new Button( width/2, height/2+250, 18, "Home", 100, 30, new Home() )
    );
  }
}

class LeaderboardPage extends Page{
  boolean global = false; //if false this means local
  LinkedList<String> scores;

  SelectButton changer;
  void setup(){
    //LEADERBOARD LABEL
    elements.add(
      new Label(width/2, 150, 100, "Leaderboard")
    );
    //HOME PAGE BUTTON
    elements.add(
      new Button( width/2, height/2+290, 18, "Home", 100, 30, new Home() )
    );
    //CHANGE FROM GLOBAL AND LOCAL
    changer = new SelectButton(width/2, height/2+250, 18, "Local", 100, 30);
    elements.add( changer );
    //LEADERBAORD
    elements.add( new Label(width/2, height/2-200, 20, "Rank:       Name:       RealWPM:       Accuracy:        RawWPM:")   );
    renderLeaderboard();
    elements.add( new ScrollLabelList(width/2, height/2-150, scores )  );
  }
  void process(){
    if( changer.selected ){
      changer.selected = false;
      global = !global;
      renderLeaderboard();
      if( global ) changer.setText("Global");
      else changer.setText("Local");
    }
    ((ScrollLabelList) elements.get(4)).labels = scores;    
    super.process();
  }

  void renderLeaderboard(){
    scores = new LinkedList<String>();
    ArrayList<Entry> entries;
    if( global ){
      entries = Leaderboard.globalLeaderboard();
    }
    else{
      entries = Leaderboard.localLeaderboard();
    }
    for( int i = 0; i < entries.size(); i++){
      Entry current = entries.get(i);
      scores.add( String.format( "%d:          %s          %3.2f          %3.2f%%          %3.2f", i+1, current.getName(), current.realWPM(), current.accuracy()*100, current.rawWPM() ) );
    }
  }

}

class Test extends Page{
  void setup(){

    LinkedList<String> ls = new LinkedList<String>();
    
    for( int i = 0; i < 100; i ++ ){
      ls.add( i + " stuff" );
    }

    elements.add( new ScrollLabelList( height/2, width/2, ls ) );

  }
}