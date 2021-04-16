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
    private final Thread reader;
    private final PipedInputStream pin=new PipedInputStream();
    private boolean quit;
    private JTextArea txtArea = new JTextArea("Enter text here...");

    public static String input = "No input yet.";
    public static JTextArea textArea;

    Thread errorThrower; // just for testing

    public Console()
    {
        // create all components and add them
        frame = new JFrame("Capulet Manor v1.0");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = new Dimension((screenSize.width - screenSize.width/4),(screenSize.height - screenSize.height/4));
        int x = frameSize.width / 2;
        int y = frameSize.height / 2;

        textArea = new JTextArea();
        textArea.setEditable(false);

        frame.setBounds(x,y,frameSize.width,frameSize.height);
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
        txtArea.setFont(txtArea.getFont().deriveFont(24f));
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
        textArea.setFont(textArea.getFont().deriveFont(25f));

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

        quit = false; // signal for exit

        // starting two separate threads to read from the PipedInputStreams
        reader=new Thread(this);
        reader.setDaemon(true);
        reader.start();

        // omittable testing part
        System.out.println("Welcome to Capulet Manor!\n\nShall we begin?!");
        System.out.println("Type Start to begin the game!");
    }

    ////////////// Frame events ///////////////////////////////////////////////////
    public synchronized void windowClosed(WindowEvent evt)
    {
        quit=true;
        this.notifyAll(); // stop all threads
        try { reader.join(1000);pin.close(); } catch (Exception e){}
        System.exit(0);
    }

    public synchronized void windowClosing(WindowEvent evt)
    {
        frame.setVisible(false); // default behavior of JFrame
        frame.dispose();
    }
    ///////////////////// end of frame events ////////////////////////////////////

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

        // just for testing
        if (Thread.currentThread()==errorThrower)
        {
            try { this.wait(1000); } catch(InterruptedException ie){}
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
        } while( !input.endsWith("\n") &&  !input.endsWith("\r\n") && !quit);
        return input;
    }

    public static void main(String[] arg)
    {
        new Console(); // create console with no reference

    }

    //////////////////////////// keyboard trigger events ////////////////////////////////
    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();
        if(id==10){
            input = txtArea.getText().trim();
            String[] splitinput = input.trim().split(" ");
            Console.textArea.setText("");
            Verbs.IdentifyInput(splitinput[0].toLowerCase(), String.join(" ",Arrays.copyOfRange(splitinput, 1, splitinput.length)));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode() ;
        if(id==10) {
            txtArea.setText("");
        }
        // do nothing
    }
    //////////////////////////// end of keyboard trigger events ////////////////////////////////
}