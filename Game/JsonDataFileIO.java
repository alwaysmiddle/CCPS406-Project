
//singleton object only 1 instance exists per game.
public class JsonDataFileIO {
    private static JsonDataFileIO jsonIoInstance = null;
    public Object ReadFile(){
        return null;
    }

    public void WriteToJsonFile(Object fileToWrite)
    {
        //write file to game folder here
    }

    public static JsonDataFileIO getInstance()
    {
        if (jsonIoInstance == null){
            jsonIoInstance = new JsonDataFileIO();
        }
        return jsonIoInstance;
    }
}
