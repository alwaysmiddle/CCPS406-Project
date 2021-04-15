import java.lang.reflect.Array;
import java.util.*;

public class CommandParsing {
    private static final String[] Verbs = {"go", "take", "inventory", "use", "eat", "look", "status", "save", "load", "restart", "talk", "start"};
    private static final String[] Prepositions = {"to", "at", "up", "into", "using"};
    private static final String[] Articles = {"a", "an", "the"};

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

    public static String RunCommand(String inputStr){
        List<String> wordlist;
        String s = "ok";
        String lowstr = inputStr.trim().toLowerCase();

        if(lowstr.equals("")){
            s= "You must enter a command";
        }else{
            wordlist = WordList(lowstr);
            wordlist.forEach((astr) -> System.out.println(astr));
            //parsecommand here;
        }
        return s;
    }

    public static void ParseCommand(List<String> wordList){
        String verb;
        String proposition;
        List<String> commands = new ArrayList<>(Arrays.asList(Verbs));
        List<String> prepositions = new ArrayList<>(Arrays.asList(Prepositions));
        List<String> articles = new ArrayList<>(Arrays.asList(Articles));

        if(wordList.size() > 2 ) {
            System.out.println("bigger than 2 words list");
        }else{
            System.out.println("This is a big table");
        }

    }
}
