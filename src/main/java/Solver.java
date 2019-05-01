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

    /**
     * Creates the timer that provides the user with 60 seconds to guess the correct password
     * @return returns the Timer
     */

    public static Timer createTimer() {
        final int MILLISEC = 1000;
        /* added to SECONDS to account for delay from running the start command and unlocking
        * the buttons and password field*/
        final int BUFFER = 2;
        final int SECONDS = 60+BUFFER;

        timer = new Timer(1 * MILLISEC, new ActionListener() {
            // calculates the time that the game should end based on start time and SECONDS
            long ENDTIME = (new Date().getTime() + SECONDS*MILLISEC);

            public void actionPerformed(ActionEvent e) {
                // calculates remaining time for game
                long remainingTime = (ENDTIME - new Date().getTime())/MILLISEC;
                if (remainingTime <= 0) { //Unable to crack the code before timer reaches 0
                    exitLabel.setVisible(true);
                    exitLabel.setText("ACCESS DENIED");
                    exitLabel.setForeground(Color.RED);
                    exitLabel.setBorder(new LineBorder(Color.RED, 3));
                    disableDecode(); //disable button functionality
                    exitButton.setVisible(true);
                    pane.add(panelExit, BorderLayout.CENTER);
                    ((Timer)e.getSource()).stop();
                }

                countDown.setText("Time remaining: " + remainingTime +" "); //display remaining time
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

        /* Create the start button */
        startButton = new JButton("Start Decoding");
        startButton.addActionListener(new StartListener());
        /* Add start button to the GUI */
        gridStart.add(startButton);
        gridStart.add(new JLabel(""));
        gridStart.add(new JLabel(""));
        gridStart.setLayout(new GridLayout(3,1));
        gridStart.setSize(100,100);
        panelStart.add(gridStart, BorderLayout.CENTER);

        /* Create exit label - start as empty string that changes based on whether
         * password is guessed correctly or not */
        exitLabel = new JLabel("", JLabel.CENTER);
        exitLabel.setPreferredSize(new Dimension(60,60));
        exitLabel.setVisible(false);
        exitLabel.setFont(new Font("Monospaced", Font.BOLD, 25));
        gridExit.add(exitLabel);
        /* Create exit button */
        exitButton = new JButton("Exit");
        exitButton.setVisible(false);
        exitButton.addActionListener(new ExitListener());
        gridExit.setBackground(Color.BLACK);

        /* Add exit button to GUI */
        gridExit.add(exitButton);
        gridExit.setLayout(new GridLayout(3,1));
        gridExit.setSize(100,100);
        panelExit.add (gridExit, BorderLayout.NORTH);

        /* Create cheat button */
        cheatButton = new JButton("Cheat you weakling");
        cheatButton.addActionListener(new CheatListener());
        /* Add cheat button to top left corner */
        panelNorthNorth.add(cheatButton, BorderLayout.WEST);

        /* Create time remaining label */
        countDown = new JLabel ("Time remaining: "+" ", JLabel.CENTER);
        countDown.setFont(new Font("Monospaced", Font.BOLD, 15));
        countDown.setForeground(Color.RED);
        /* Add button to top right corner */
        panelNorthNorth.add(countDown, BorderLayout.EAST);

        /* Create password field for user to input guesses */
        inputPassword = new JPasswordField( 30);
        inputPassword.setPreferredSize(new Dimension(80,80));
        inputPassword.setBackground(Color.BLACK);
        inputPassword.setBorder(new LineBorder(Color.GREEN, 2));
        inputPassword.setForeground(Color.GREEN);
        inputPassword.setFont(new Font("Verdana", Font.BOLD, 60));

        /* Create submit button - allows user's input to be evaluated */
        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(30,30));
        submitButton.setBackground(Color.RED);
        submitButton.setBackground(Color.BLUE);
        submitButton.addActionListener(new SubmitListener());

        panelNorth.add(panelNorthNorth, BorderLayout.NORTH);

        /* To add the input field and submit button to the center of the screen
        * a grid layout was used; necessary to loop through the grid to get to the
        * center of the screen */
        for (int i  =0 ; i<2; i++){ gridNorth.add(new JLabel("")); }

        gridNorth.add(inputPassword);
        gridNorth.add(submitButton);

        gridNorth.setLayout(new GridLayout(4,3));
        gridNorth.setSize(30,100);
        panelNorth.add (gridNorth, BorderLayout.CENTER);

        /* These labels provide "clues" to user for how close they are to the
        * correct password; they are updated after each try */
        placedCorrectly  = new JLabel("Percent of Characters Placed Correctly", JLabel.CENTER);
        placedCorrectly.setFont(new Font("Monospaced", Font.BOLD, 15));
        placedCorrectly.setForeground(Color.YELLOW);
        charsCorrect  = new JLabel("Percent of Characters Entered Correctly", JLabel.CENTER);
        charsCorrect.setForeground(Color.YELLOW);
        charsCorrect.setFont(new Font("Monospaced", Font.BOLD, 15));
        cheatResult = new JLabel("Cheat Result:", JLabel.CENTER);
        cheatResult.setForeground(Color.RED);
        cheatResult.setFont(new Font("Monospaced", Font.BOLD, 15));
        lengthCorrect = new JLabel("Percent of total length", JLabel.CENTER);
        lengthCorrect.setForeground(Color.YELLOW);
        lengthCorrect.setFont(new Font("Monospaced", Font.BOLD, 15));

        gridText.add(placedCorrectly);
        gridText.add(charsCorrect);
        gridText.add(cheatResult);
        gridText.add(lengthCorrect);
        panelSouth.add (gridText, BorderLayout.NORTH);

        gridText.setLayout(new GridLayout(5,3));
        gridText.setSize(30,100);

        /* Display the start button and disable functionality of all other components */
        drawStartButton(panelStart);
        disableDecode();
        /* Add components to the GUI pane */
        pane.add(panelNorth,BorderLayout.NORTH);
        pane.add(panelSouth, BorderLayout.SOUTH);
    }

    /** Application starts here */
    public static void main(String[] args) {
        genPass = new GenPass(); //generate a random password from built in dictionary to be guessed by user
        // will check all input passwords against the generated password and return data used in the clues
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
    /** Event handler for cheat button
     *  Displays the computer's closest guess to the user
     */
    private static class CheatListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Cheater cheater = new Cheater(codeChecker);
            String cheatPassword = cheater.doCheat(); // provides the computer's guess for the password
            cheatResult.setText("Loser. Here's a hint for you: " + cheatPassword); //display cheat password to user
        }
    }

    /** Displays the start button in the center of the screen */
    private static void drawStartButton(JPanel startPanel){
        pane.add(startPanel, BorderLayout.CENTER);
    }
    /** Disable the functionality of all game buttons
     *  Disabled buttons: password field; submit button; cheat button
     * */
    private static void disableDecode() {
        inputPassword.setVisible(false);
        inputPassword.setEnabled(false);
        submitButton.setEnabled(false);
        submitButton.setVisible(false);
        cheatButton.setEnabled(false);
    }

    /** Event handler for start button
     *  Starts the timer once start has been clicked
     */
    private static class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            startDecode();
            timer.start();
        }
        /**
         * Enables buttons that had been disabled prior
         * Enabled buttons: input field; submit button
         */
        private void startDecode() {
            timer = createTimer();
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

    /* Event handler for Solve button */
    private static class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (inputPassword.getPassword() != null) {
                //decode purposes: displays correct password on command line
                System.out.println("real password: " + genPass.getPassword());
                char[] input = inputPassword.getPassword();
                /* The user's input matches the correct/generated password */
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
                }
                /* User's input does not match the correct password; provide feedback based on their guess */
                else {
                    float percentLengthCorrect = codeChecker.percentageCorrectLength(input);
                    lengthCorrect.setText(percentLengthCorrect > -1 ? "Your input length is " +
                            percentLengthCorrect +"% of the length of the correct password." :
                            "Your length is too long");
                    placedCorrectly.setText("Percent of Characters Placed Correctly");
                    charsCorrect.setText("Percent of Characters Entered Correctly");

                    /* Only provide feedback on correct chars and placement if the input len == correct password len */
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
} // end Solver
