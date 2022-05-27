import java.util.ArrayList;

public class WordGenerator{
  
  private static ArrayList<String> words = new ArrayList<String>();

  public static void addWord( String word ){
      System.out.println( word );
      words.add( word );
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
