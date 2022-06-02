
public class Tester{

    public static void main( String[] args){
        
AWTEventListener listener = new AWTEventListener() {
  @Override
  public void eventDispatched(AWTEvent event) {
    try {
      KeyEvent evt = (KeyEvent)event;
      if(evt.getID() == KeyEvent.KEY_PRESSED && evt.getModifiers() == KeyEvent.CTRL_MASK && evt.getKeyCode() == KeyEvent.VK_F) {

      }
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
};

    }
}


AWTEventListener listener = new AWTEventListener() {
  @Override
  public void eventDispatched(AWTEvent event) {
    try {
      KeyEvent evt = (KeyEvent)event;
      if(evt.getID() == KeyEvent.KEY_PRESSED && evt.getModifiers() == KeyEvent.CTRL_MASK && evt.getKeyCode() == KeyEvent.VK_F) {

      }
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
};