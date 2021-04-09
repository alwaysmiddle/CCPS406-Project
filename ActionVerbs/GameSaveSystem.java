import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class GameSaveSystem {
    //public static final Map<String, Integer> gameStartMenu = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private static int _currentSaveIndex = 0;
    //save and load file here in this location

    public static void init(){
//        gameStartMenu.put("new game", 1);
//        gameStartMenu.put("load", 2);

    }

//    public static void GameMenu(){
//
//    }


    public static int getCurrentSaveIndex() {
        return _currentSaveIndex;
    }

    public static void setCurrentSaveNum(int _currentSaveNum) {
        _currentSaveIndex = _currentSaveNum;
    }

//    //start a new game
//    case 1 -> {
//
//        //System.out.println(player.getCurrentStage());
//    }
//    //Loading the game
//            case 2 -> {
//
//        //System.out.println(player.getCurrentStage());
//    }
//    //Loading the game
//            case 3 -> {
//
//        //System.out.println(player.getCurrentStage());
//    }
}
