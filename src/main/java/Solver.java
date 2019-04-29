import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *  Class that runs the GUI for decode game
 *
 *  @authors Faith Kim and Hafsah Hanif
 *  @version CSC 260 FINAL PROJECT, 4/30/2019
 */
public class Solver extends JApplet {


    private static JFrame frame;
    private static Container pane;

    private static CodeChecker codeChecker;
    private static GenPass genPass;

    private static JButton cheatButton;
    private static JButton startButton;
    private static JButton exitButton;
    private static JButton submitButton;

    private static JPanel gridExit;
    private static JPanel panelExit;

    private static JPasswordField inputPassword;

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
        frame.setSize(700,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);

        // Add components
        createComponents(frame.getContentPane());

        // Display the window.
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
                if (diff <= 0) { //Unable to crack the code before timer reaches 0
                    exitLabel.setVisible(true);
                    exitLabel.setText("ACCESS DENIED");
                    disableDecode();
                    exitButton.setVisible(true);
                    pane.add(panelExit, BorderLayout.CENTER);
                    ((Timer)e.getSource()).stop();
                }
                countDown.setText("Time remaining: " + diff+" "); //display remaining time
                countDown.repaint();
            }
        });
        return timer;
    }


    public static void createComponents(Container p) {
        pane = p;

        JPanel panelNorth = new JPanel();
        panelNorth.setBackground(Color.BLACK);
        panelNorth.setLayout(new BorderLayout());

        JPanel panelSouth = new JPanel();
        panelSouth.setBackground(Color.BLACK);
        panelSouth.setLayout(new BorderLayout());

        final JPanel panelNorthNorth = new JPanel();
        panelNorthNorth.setBackground(Color.BLACK);
        panelNorthNorth.setLayout(new BorderLayout());

        JPanel panelStart = new JPanel();
        panelStart.setBackground(Color.BLACK);
        panelStart.setLayout(new BorderLayout());

        JPanel gridNorth = new JPanel();
        gridNorth.setBackground(Color.BLACK);
        gridNorth.setLayout(new GridLayout());

        JPanel gridText = new JPanel();
        gridText.setBackground(Color.BLACK);
        gridText.setLayout(new GridLayout());


        JPanel gridStart = new JPanel();
        gridStart.setBackground(Color.BLACK);
        gridStart.setLayout(new GridLayout());

        panelExit = new JPanel();
        panelExit.setBackground(Color.BLACK);
        panelExit.setLayout(new BorderLayout());

        gridExit = new JPanel();
        gridExit.setBackground(Color.BLACK);
        gridExit.setLayout(new GridLayout());

        startButton = new JButton("Start Decoding");
        startButton.addActionListener(new StartListener());

        gridStart.add(startButton);
        gridStart.add(new JLabel(""));
        gridStart.add(new JLabel(""));
        gridStart.setLayout(new GridLayout(3,1));
        gridStart.setSize(100,100);
        panelStart.add(gridStart, BorderLayout.CENTER);

        exitLabel = new JLabel("", JLabel.CENTER);
        exitLabel.setPreferredSize(new Dimension(60,60));
        exitLabel.setVisible(false);
        exitLabel.setForeground(Color.RED);
        exitLabel.setBorder(new LineBorder(Color.RED, 3));
        exitLabel.setFont(new Font("Monospaced", Font.BOLD, 25));
        gridExit.add(exitLabel);

        exitButton = new JButton("Exit");
        exitButton.setVisible(false);
        exitButton.addActionListener(new ExitListener());
        gridExit.setBackground(Color.BLACK);
        gridExit.add(exitButton);

        gridExit.setLayout(new GridLayout(3,1));
        gridExit.setSize(100,100);
        panelExit.add (gridExit, BorderLayout.NORTH);

        cheatButton = new JButton("Cheat you weakling");
        cheatButton.addActionListener(new CheatListener());
        panelNorthNorth.add(cheatButton, BorderLayout.WEST);

        countDown = new JLabel ("Time remaining: "+" ", JLabel.CENTER);
        countDown.setFont(new Font("Monospaced", Font.BOLD, 15));
        countDown.setForeground(Color.RED);
        panelNorthNorth.add(countDown, BorderLayout.EAST);

        inputPassword = new JPasswordField( 30);
        inputPassword.setPreferredSize(new Dimension(80,80));
        inputPassword.setBackground(Color.BLACK);
        inputPassword.setBorder(new LineBorder(Color.GREEN, 2));
        inputPassword.setForeground(Color.GREEN);
        inputPassword.setFont(new Font("Verdana", Font.BOLD, 60));



        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(30,30));
        submitButton.setBackground(Color.RED);
        submitButton.setBackground(Color.BLUE);
        submitButton.addActionListener(new SubmitListener());



        panelNorth.add(panelNorthNorth, BorderLayout.NORTH);

        for (int i  =0 ; i<2; i++){
            gridNorth.add(new JLabel(""));
        }

        gridNorth.add(inputPassword);

        gridNorth.add(submitButton);

        gridNorth.setLayout(new GridLayout(4,3));
        gridNorth.setSize(30,100);
        panelNorth.add (gridNorth, BorderLayout.CENTER);

        placedCorrectly  = new JLabel("Placed Correctly", JLabel.CENTER);
        placedCorrectly.setFont(new Font("Monospaced", Font.BOLD, 15));
        placedCorrectly.setForeground(Color.YELLOW);
        charsCorrect  = new JLabel("Chars correct:", JLabel.CENTER);
        charsCorrect.setForeground(Color.YELLOW);
        charsCorrect.setFont(new Font("Monospaced", Font.BOLD, 15));
        cheatResult = new JLabel("Cheat Result:", JLabel.CENTER);
        cheatResult.setForeground(Color.RED);
        cheatResult.setFont(new Font("Monospaced", Font.BOLD, 15));
        lengthCorrect = new JLabel("Length correct: ", JLabel.CENTER);
        lengthCorrect.setForeground(Color.YELLOW);
        lengthCorrect.setFont(new Font("Monospaced", Font.BOLD, 15));

        drawStartButton(panelStart);
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
            cheatResult.setText("Loser. Here's a hint for you: " + cheatPassword);
        }
    }
    private static void drawStartButton(JPanel startPanel){
        pane.add(startPanel, BorderLayout.CENTER);
    }
    private static void disableDecode() {
        inputPassword.setVisible(false);
        inputPassword.setEnabled(false);
        submitButton.setEnabled(false);
        submitButton.setVisible(false);
        cheatButton.setEnabled(false);
    }

    private static class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            startDecode();
            timer.start();
        }



        private void startDecode() {
            inputPassword.setVisible(true);
            startButton.setVisible(false);
            submitButton.setEnabled(true);
            submitButton.setVisible(true);
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
                    exitLabel.setText("ACCESS GRANTED");
                    exitLabel.setForeground(Color.GREEN);
                    exitLabel.setBorder(new LineBorder(Color.GREEN, 3));
                    exitLabel.setVisible(true);
                    disableDecode();
                    exitButton.setVisible(true);
                    pane.add(panelExit, BorderLayout.CENTER);
                    timer.stop();
                } else {

                    float percentLengthCorrect = codeChecker.percentageCorrectLength(input);
                    lengthCorrect.setText(percentLengthCorrect > -1 ? "Your input length is " + percentLengthCorrect +"% of the length of the correct password." :
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
