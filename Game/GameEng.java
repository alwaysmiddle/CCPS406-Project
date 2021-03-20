import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

public class GameEng extends JFrame{

    private JPanel panel = new JPanel();
    private JTextArea txtArea = new JTextArea();
    private boolean isRunning = false;
    private int windowWidth = 940, windowHeight = 640;
    private long fps = 6;
    private int x = 0;

    public static void main(String[] args){
        GameEng gc = new GameEng();
        gc.run();
        System.exit(0);
    }

    //method for start and running loop
    public void run(){
        initialize();
        isRunning = true;
        while(isRunning){
            long time = System.currentTimeMillis();
            //call read input and write to panel method
            update();
            write();
            // delay per frame - the time for each frame
            time = (1000 / fps) - (System.currentTimeMillis() - time);
            if (time > 0 ){
                try
                {
                    Thread.sleep(time);
                }
                catch(Exception e){}
            }
        }
        setVisible(false);
    }

    //method for setting up the game
    void initialize(){
        setTitle("Capulet Manor v1.0");
        setSize(windowWidth, windowHeight);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        add(txtArea);
    }

    //will check input and update any parameters
    void update(){
        x++;
    }

    //will send the respective text
    void write(){
        try{
        Scanner fileIn = new Scanner(new File("Game\\RandomText"));
        String fileContent = "";
        while(fileIn.hasNext()){
             fileContent = fileContent.concat(fileIn.nextLine() + "\n");
        }
        txtArea.setText(fileContent);
        txtArea.setEditable(false);
        fileIn.close();
        }catch (FileNotFoundException e){
            System.out.println("Invalid Input.");
        }
    }
}
