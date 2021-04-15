import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CommandParsing {
    public String[] Verbs;
    public String[] Prepositions;
    public String[] Articles;

    public static List<String> WordList(String input){
        String delims = " \t,.:;?!\"'";
        List<String> strList = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input , delims);
        String t;

        while(tokenizer.hasMoreTokens()){
            t = tokenizer.nextToken();
            strList.add(t);
        }
        return strList;
    }
}
