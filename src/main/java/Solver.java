import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.TimerTask;
//import java.util.Timer;
import javax.swing.Timer;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *  Class that runs a maze display/solution GUI
 *
 *  @author Nicholas R. Howe
 *  @version CSC 112, 20 March 2006
 */
public class Solver extends JApplet {

    /** The window */

    private static JFrame frame;

    private static CodeChecker codeChecker;

    private static GenPass genPass;

    /** Solve button */
    private static JButton solveButton;

    private static JButton cheatButton;

    private static JButton startButton;

    private static JButton exitButton;


    private static JPasswordField inputPassword;

    private static JLabel title;

    private static JButton submitButton;

    private static JLabel exitLabel;
    private static JLabel  placedCorrectly;
    private static JLabel charsCorrect;
    private static JLabel cheatResult;
    private static JLabel lengthCorrect;
    private static JLabel countDown;
    private static Timer timer;


    public static void createAndShowGUI() {
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        frame = new JFrame("Crack the Code!");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);

        // Add components
        createComponents(frame.getContentPane());

        // Display the window.
//        frame.pack();
        frame.setVisible(true);
    }

    public static Timer createTimer() {
        final int milliSec = 1000;
        final int buffer = 6;
        final int sec = 60+buffer;

        timer = new Timer(1 * milliSec, new ActionListener() {
            long endTime = (new Date().getTime() + sec*milliSec);
            public void actionPerformed(ActionEvent e) {
                long diff = (endTime - new Date().getTime())/1000;
                if (diff <= 0) {
                    exitLabel.setVisible(true);
                    exitLabel.setText("ACCESS DENIED");
                    disableDecode();
                    exitButton.setVisible(true);
                    ((Timer)e.getSource()).stop();
                }
                countDown.setText("Time remaining: " + diff+" ");
                countDown.repaint();
            }
        });

        return timer;

    }


    public static void createComponents(Container pane) {
        //TODO create JComponent for code cracker
        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(Color.BLACK);
        JPanel panelNorth = new JPanel();
        panelNorth.setBackground(Color.BLACK);
        JPanel panelSouth = new JPanel();
        panelSouth.setBackground(Color.BLACK);
        final JPanel panelNorthNorth = new JPanel();
        JPanel gridNorth = new JPanel();
        JPanel gridText = new JPanel();
        gridNorth.setBackground(Color.BLACK);
        gridText.setBackground(Color.BLACK);

        panelSouth.setLayout(new BorderLayout());
        panelNorth.setLayout(new BorderLayout());
        gridNorth.setLayout(new GridLayout());
        gridText.setLayout(new GridLayout());
        panelNorthNorth.setLayout(new BorderLayout());
        panelCenter.setLayout(new BorderLayout());

        panelCenter.setPreferredSize(new Dimension(50, 50));

        exitButton = new JButton("Exit");
        exitButton.setVisible(false);
        panelNorthNorth.add(exitButton, BorderLayout.SOUTH);
        exitButton.addActionListener(new ExitListener());
        panelNorthNorth.setBackground(Color.BLACK);

        exitLabel = new JLabel("", JLabel.CENTER);
        exitLabel.setVisible(false);
        exitLabel.setForeground(Color.RED);
        panelNorthNorth.add(exitLabel, BorderLayout.NORTH);

        startButton = new JButton("Start Decoding");
        startButton.addActionListener(new StartListener());
        panelNorthNorth.add(startButton, BorderLayout.CENTER);
        cheatButton = new JButton("Cheat you weakling");
        cheatButton.addActionListener(new CheatListener());
        panelNorthNorth.add(cheatButton, BorderLayout.WEST);

        title = new JLabel("Guess the password you fool!!");
//        pane.add(title);

        countDown = new JLabel ("Time remaining: "+" ", JLabel.CENTER);
        countDown.setFont(new Font("Monospaced", Font.BOLD, 15));
        countDown.setForeground(Color.RED);
        panelNorthNorth.add(countDown, BorderLayout.EAST);

        inputPassword = new JPasswordField( 30);
        inputPassword.setPreferredSize(new Dimension(50,50));
        inputPassword.setBackground(Color.BLACK);
        inputPassword.setBorder(new LineBorder(Color.GREEN, 2));
        inputPassword.setForeground(Color.GREEN);
        inputPassword.setFont(new Font("Verdana", Font.BOLD, 30));



        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(30,30));
        submitButton.setBackground(Color.RED);
        submitButton.setOpaque(true);
        submitButton.addActionListener(new SubmitListener());



        panelNorth.add(panelNorthNorth, BorderLayout.NORTH);

        for (int i  =0 ; i<3; i++){
             gridNorth.add(new JLabel(""));
        }

        gridNorth.add(inputPassword);

        gridNorth.add(submitButton);

        gridNorth.setLayout(new GridLayout(5,3));
        gridNorth.setSize(30,100);

        panelNorth.add (gridNorth, BorderLayout.CENTER);

        placedCorrectly  = new JLabel("Placed Correctly", JLabel.CENTER);
        placedCorrectly.setForeground(Color.YELLOW);
        charsCorrect  = new JLabel("Chars correct:", JLabel.CENTER);
        charsCorrect.setForeground(Color.YELLOW);
        cheatResult = new JLabel("Cheat Result:", JLabel.CENTER);
        cheatResult.setForeground(Color.RED);
        lengthCorrect = new JLabel("Length correct: ", JLabel.CENTER);
        lengthCorrect.setForeground(Color.YELLOW);

        disableDecode();
        timer = createTimer();


        gridText.add(placedCorrectly);
        gridText.add(charsCorrect);
        gridText.add(cheatResult);
        gridText.add(lengthCorrect);

        gridText.setLayout(new GridLayout(5,3));
        gridText.setSize(30,100);

        panelSouth.add (gridText, BorderLayout.NORTH);

       pane.add(panelNorth,BorderLayout.NORTH);
        pane.add(panelSouth, BorderLayout.SOUTH);
    }


    private static void disableDecode() {
        inputPassword.setEnabled(false);
        submitButton.setEnabled(false);
        cheatButton.setEnabled(false);
    }

    // Application starts here
    public static void main(String[] args) {


        genPass = new GenPass();
        codeChecker = new CodeChecker(genPass.getPassword());

        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();


            }
        });

    }

    // Applet starts here
    public void init() {
        //Execute a job on the event-dispatching thread:
        //creating this applet's GUI.
        try {
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    createComponents(getContentPane());
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't successfully complete");
        }
    }
    private static class CheatListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           Cheater cheater = new Cheater(codeChecker);
           String cheatPassword = cheater.doCheat();
           cheatResult.setText("Loser. Here's just a lil bit of hint for you: " + cheatPassword);
        }
    }

    private static class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            startDecode();
            timer.start();
        }

        private void startDecode() {
            startButton.setVisible(false);
            submitButton.setEnabled(true);
            cheatButton.setEnabled(true);
            inputPassword.setEnabled(true);
        }
    }

    private static class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(-1);
        }

    }



//     Event handler for Solve button */
    private static class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (inputPassword.getPassword() != null) {
                System.out.println("real password: " + genPass.getPassword());
                char[] input = inputPassword.getPassword();
                if (codeChecker.checkPassword(input)) {
                    submitButton.setEnabled(false);
                    exitLabel.setText("You win! Congrats!");
                    exitLabel.setVisible(true);
                    disableDecode();
                    exitButton.setVisible(true);
                    timer.stop();
//                    System.exit(-1);
                } else {

                    float percentLengthCorrect = codeChecker.percentageCorrectLength(input);
                    lengthCorrect.setText(percentLengthCorrect > -1 ? "Your input length is " + percentLengthCorrect +"% correct." :
                            "Your length is too long");
                    if (codeChecker.inputLengthMatchesPasswordLength(input)) {
                        float percentPlacedCorrect = codeChecker.percentagePlacedCorrectly(input);
                        float percentCorrectInput = codeChecker.percentageCorrectInput(input);
                        placedCorrectly.setText("You have placed "+percentPlacedCorrect+"% of characters correctly!\n");
                        charsCorrect.setText("You have input " + percentCorrectInput+ "% of characters correctly!\n");

                    }

                }
            }
        }
    }

}
