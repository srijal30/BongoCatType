import java.util.LinkedList;

public class WordGenerator{
  
  private static WordBST words = new WordBST();

  public static void addWord( String word ){
      words.insert( word );
  }

  public static void generate( int diff, int wC, LinkedList<String> queue ){
    //randomized starting position
    //int walkCount = (int) (Math.random() * words.height()*diff/5 + words.height()*(diff-1)/5); 
    int walkCount = 100;
    //walk to that starting pos
    TreeNode walker = words.walk( walkCount );
    //now keep on walking (random steps) until queue is full
    while( wC-- > 0 ){
      queue.addLast( walker.getValue() );
      walker = words.walk( (int) (Math.random() * 10) );
    }
  }

  //FOR TESTING
  public static void printWords(){
    System.out.println( words.inOrderTravStr() );
  }
}

//taken from library and modified
//in this version all harder words to right and easier to left
class WordBST
{
  TreeNode _root;
  WordBST() { _root = null; }
  public void insert( String newVal )
  {
    TreeNode newNode = new TreeNode( newVal );
    if ( _root == null ) {
      _root = newNode;
      return;
    }
    insert( _root, newNode );
  }
  //recursive helper for insert(int)
  public void insert( TreeNode stRoot, TreeNode newNode )
  {
    if ( newNode.getDiff() < stRoot.getDiff() ) {
      if ( stRoot.getLeft() == null )
        stRoot.setLeft( newNode );
      else
        insert( stRoot.getLeft(), newNode );
      return;
    }
    else {
      if ( stRoot.getRight() == null )
        stRoot.setRight( newNode );
      else
        insert( stRoot.getRight(), newNode );
      return;
    }
  }//end insert()

  public int height(){
    return height(_root);
  }
  public int height(TreeNode cur){
    if( cur == null ) return -1;
    return Math.max( height(cur.getLeft()) , height(cur.getRight()) ) + 1;
  }

  //WE NEED TO MAKE SURE THIS DOESNT CAUSE ERROR CUZ WALKS OUT OF BOUNDS
  public TreeNode walk(int count){
    return walk(count, _root);
  }
  public TreeNode walk( int count, TreeNode cur){
    if ( count == 0 ) return cur;
    double leftRight = Math.random();

    if ( cur.getLeft() == null ) return walk( --count, cur.getRight() );
    if ( cur.getRight() == null ) return walk( --count, cur.getLeft() );


    if ( leftRight > .5 ) return walk( --count, cur.getLeft() );
    else return walk( --count, cur.getRight() ); 
  }


  ///////FOR TESTING will remove soon
  public String inOrderTravStr(){
    return inOrderTravStr( _root );
  }
  public String inOrderTravStr( TreeNode currNode ) {
    String retStr = "";
    if ( currNode == null )
      return retStr;
    retStr += inOrderTravStr( currNode.getLeft() );
    retStr += " " + currNode.getValue() + "\n";
    retStr += inOrderTravStr( currNode.getRight() );
    return retStr;
  }
}//end class

//also taken from library
class TreeNode
{
  int difficulty; //will store difficulty of string
  String _cargo;
  TreeNode _lt, _rt; 
  TreeNode( String initValue ){
    _cargo = initValue;
    updateDiff();
  }
  TreeNode( String initValue, TreeNode initLeft, TreeNode initRight ){
    this(initValue);
    _lt = initLeft;
    _rt = initRight;
  }
  //MODIFICATIONS
  //THIS IS WHERE WE WILL SET UP DIFFICULTY GENERATION CODE STUFF (WHAT DETERMINES DIFFICULTY)
  int getDiff() { return difficulty; }
  void updateDiff() { difficulty = _cargo.length(); }
  //GETTERS
  TreeNode getLeft() { return _lt; }
  TreeNode getRight() { return _rt; }
  String getValue() { return _cargo; }
  //SETTERS
  void setLeft( TreeNode theNewLeft ) { _lt = theNewLeft; }
  void setRight( TreeNode theNewRight ) { _rt = theNewRight; }
  void setValue( String theNewValue ) { _cargo = theNewValue; updateDiff(); }
}
