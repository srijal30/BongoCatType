import java.util.ArrayList;

public class WordGenerator{

    private static ArrayList<String> words = new ArrayList<String>();

    public static void addWord( String word ){
        System.out.println( word );
        words.add( word );
    }


}

