import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;

public class Console extends WindowAdapter implements WindowListener, Runnable, KeyListener {
    private final JFrame frame;
    public static JTextArea textArea;
    private final Thread reader;
    //private final Thread reader2;
    private boolean quit;
    private JTextArea txtArea = new JTextArea("Enter text here...");
    public static String input = "No input yet.";

    private final PipedInputStream pin=new PipedInputStream();
    //private final PipedInputStream pin2=new PipedInputStream();

    Thread errorThrower; // just for testing (Throws an Exception at this Console

    public Console()
    {
        // create all components and add them
        frame=new JFrame("Capulet Manor v1.0");
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize=new Dimension((screenSize.width - screenSize.width/4),(screenSize.height - screenSize.height/4));
        int x=frameSize.width/2;
        int y=frameSize.height/2;
        frame.setBounds(x,y,frameSize.width,frameSize.height);

        textArea=new JTextArea();
        textArea.setEditable(false);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(new JScrollPane(textArea),BorderLayout.CENTER);
        frame.getContentPane().add(txtArea,BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        frame.addWindowListener(this);
        txtArea.addKeyListener(this);
        txtArea.setBorder(BorderFactory.createCompoundBorder(
                textArea.getBorder(),
                BorderFactory.createEmptyBorder(5,5,10,10)
        ));
        txtArea.setFont(txtArea.getFont().deriveFont(22f));
        txtArea.setBackground(Color.black);
        txtArea.setForeground(Color.white);
        textArea.requestFocus();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createCompoundBorder(
                textArea.getBorder(),
                BorderFactory.createEmptyBorder(10,15,15,20)
        ));
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.white);
        textArea.setFont(textArea.getFont().deriveFont(24f));

        try
        {
            PipedOutputStream pout=new PipedOutputStream(this.pin);
            System.setOut(new PrintStream(pout,true));
        }
        catch (java.io.IOException io)
        {
            textArea.append("Couldn't redirect STDOUT to this console\n"+io.getMessage());
        }
        catch (SecurityException se)
        {
            textArea.append("Couldn't redirect STDOUT to this console\n"+se.getMessage());
        }


        quit=false; // signals the Threads that they should exit

        // Starting two separate threads to read from the PipedInputStreams
        //
        reader=new Thread(this);
        reader.setDaemon(true);
        reader.start();

        // testing part
        // you may omit this part for your application
        //
        System.out.println("Welcome to Capulet Manor!\n\nShall we begin?!");
        System.out.println("Type Start to begin the game!");
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        String[] fontNames=ge.getAvailableFontFamilyNames();
//        for (String fontName : fontNames) System.out.println(fontName);
        // Testing part: simple an error thrown anywhere in this JVM will be printed on the Console
        // We do it with a separate Thread because we can't break a Thread used by the Console.
    }



    //region Frame events
    public synchronized void windowClosed(WindowEvent evt)
    {
        quit=true;
        this.notifyAll(); // stop all threads
        try { reader.join(1000);pin.close(); } catch (Exception e){}
        //try { reader2.join(1000);pin2.close(); } catch (Exception e){}
        System.exit(0);
    }

    public synchronized void windowClosing(WindowEvent evt)
    {
        frame.setVisible(false); // default behaviour of JFrame
        frame.dispose();
    }
    //endregion

    public synchronized void run()
    {
        try
        {
            while (Thread.currentThread()==reader)
            {
                try { this.wait(100);}catch(InterruptedException ie) {}
                if (pin.available()!=0)
                {
                    String input=this.readLine(pin);
                    textArea.append(input);
                }
                if (quit) return;
            }

        } catch (Exception e)
        {
            textArea.append("\nConsole reports an Internal error.");
            textArea.append("The error is: "+e);
        }

        // just for testing (Throw a Nullpointer after 1 second)
        if (Thread.currentThread()==errorThrower)
        {
            try { this.wait(1000); }catch(InterruptedException ie){}
            throw new NullPointerException("Application test: throwing an NullPointerException It should arrive at the console");
        }

    }

    public synchronized String readLine(PipedInputStream in) throws IOException
    {
        String input="";
        do
        {
            int available=in.available();
            if (available==0) break;
            byte[] b=new byte[available];
            in.read(b);
            input=input+new String(b,0,b.length);
        }while( !input.endsWith("\n") &&  !input.endsWith("\r\n") && !quit);
        return input;
    }

    public synchronized void updateStates(JTextArea consoleTextArea) throws Exception
    {
        //Here we updates all the backend state

    }

    public static void main(String[] arg)
    {
        new Console(); // create console with no reference

        //testing dctionary reading
        Map<String, Object> jsonFile = JsonDataFileIO.readJsonFileAsMap(GlobalReference.DICTIONARY_FILE_LOCATION);
        Map<String, Integer> verbsMap = (Map<String, Integer>) jsonFile.get("verbs");

        System.out.println(verbsMap == null);
        for (Map.Entry<String, Integer> entry : verbsMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + String.valueOf(entry.getValue()));
        }

    }

    //region keyboard trigger events
    @Override
    public void keyTyped(KeyEvent e) {
        //do absolutely nothing, useless -_-
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();
        if(id==10){
            input = txtArea.getText();

            CommandParsing.RunCommand(input);
            Console.textArea.setText("");

            //here we do checking for stage progression
            try {
                updateStates(this.txtArea);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode() ;
        if(id==10) {
            txtArea.setText("");
        }
        //do nothing
    }
    //endregion
}