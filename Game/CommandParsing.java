import java.lang.reflect.Array;
import java.util.*;

public class CommandParsing {
    private static Map<String, Integer> verbsMap;
    private static Map<String, Integer> prepositionsMap;
    private static Map<String, Integer> articlesMap;
    private static List<String> subject;
    private static String verb;

    private static void initialize() {
        Map<String, Object> jsonFile = JsonDataFileIO.readJsonFileAsMap(GlobalReference.DICTIONARY_FILE_LOCATION);
        verbsMap = (Map<String, Integer>) jsonFile.get("verbs");
        prepositionsMap = (Map<String, Integer>) jsonFile.get("prepositions");
        articlesMap = (Map<String, Integer>) jsonFile.get("articles");
    }

    private static List<String> WordList(String input){
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

    public static void RunCommand(String inputStr){
        initialize();

        List<String> wordlist;
        String s = "ok";
        String lowstr = inputStr.trim().toLowerCase();

        if(lowstr.equals("")){
            for (Map.Entry<String, Integer> entry : verbsMap.entrySet()) {
                System.out.println(entry.getKey());
            }
        }else{
            wordlist = WordList(lowstr);
            ParseCommand(wordlist);
            Verbs.IdentifyInput(getVerb(), getSubject());

//            wordlist.forEach((astr) -> System.out.println(astr));
//            System.out.println("Verb is: " + getVerb());
//            System.out.println("Subject is: " + getSubject());
        }

    }

    public static void ParseCommand(List<String> wordList){
        verb = "";
        subject = new ArrayList<>();

        for (String s : wordList){
            if (verbsMap.containsKey(s)){
                verb = s;
                break;
            }
        }

        for (String s : wordList){
            if (!verbsMap.containsKey(s) && !prepositionsMap.containsKey(s) && !articlesMap.containsKey(s)){
                subject.add(s);
            }
        }
    }

    public static String getSubject() {
        return String.join(" ", subject);
    }

    public static String getVerb() {
        return verb;
    }
}
