import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.TimerTask;
//import java.util.Timer;
import javax.swing.Timer;
import javax.swing.*;

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


    private static JPasswordField inputPassword;

    private static JLabel title;

    private static JButton submitButton;

    private static NewApplication codeCrackerApp;
    private static JLabel  placedCorrectly;
    private static JLabel charsCorrect;
    private static JLabel cheatResult;
    private static JLabel lengthCorrect;
    private static JLabel countDown;
    private static Timer timer;
    //

    public static void createAndShowGUI() {
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        frame = new JFrame("Crack the Code!");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add components
        createComponents(frame.getContentPane());

        // Display the window.
//        frame.pack();
        frame.setVisible(true);
    }


    public static void createComponents(Container pane) {
        //TODO create JComponent for code cracker
        JPanel panelCenter = new JPanel();
//        JPanel panelCenter = new JPanel(new GridLayout(2, 2));
        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();
        final JPanel panelNorthNorth = new JPanel();
        JPanel gridNorth = new JPanel();
        JPanel gridText = new JPanel();

        panelSouth.setLayout(new BorderLayout());
        panelNorth.setLayout(new BorderLayout());
        gridNorth.setLayout(new GridLayout());
        gridText.setLayout(new GridLayout());
        panelNorthNorth.setLayout(new BorderLayout());
        panelCenter.setLayout(new BorderLayout());


//        panelNorthNorth.add(timer, BorderLayout.CENTER);


        panelCenter.setPreferredSize(new Dimension(50, 50));

        startButton = new JButton("Start Decoding");
        startButton.addActionListener(new StartListener());
        panelNorthNorth.add(startButton, BorderLayout.CENTER);
        cheatButton = new JButton("Cheat you weakling");
        cheatButton.addActionListener(new CheatListener());
        panelNorthNorth.add(cheatButton, BorderLayout.WEST);

        title = new JLabel("Guess the password you fool!!");
//        pane.add(title);

        countDown = new JLabel ("Time remaining: "+"                    ", JLabel.CENTER);
        panelNorthNorth.add(countDown, BorderLayout.EAST);

        inputPassword = new JPasswordField( 30);
        inputPassword.setPreferredSize(new Dimension(50,50));
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
//        gridNorth.add(new JLabel(""));

        gridNorth.setLayout(new GridLayout(5,3));
        gridNorth.setSize(30,100);
//        gridNorth.setVisible(true);

        panelNorth.add (gridNorth, BorderLayout.CENTER);

//        panelNorth.add (gridNorth, BorderLayout.CENTER);
//        panelNorth.add(inputPassword, BorderLayout.CENTER);
//        System.out.printf("You have placed %.02f%% characters correctly!\n", percentPlacedCorrect);
//                        System.out.printf("You have input %.02f%% characters correctly!\n", percentCorrectInput);

        placedCorrectly  = new JLabel("Placed Correctly", JLabel.CENTER);
        charsCorrect  = new JLabel("Chars correct:", JLabel.CENTER);
        cheatResult = new JLabel("Cheat Result:", JLabel.CENTER);
        lengthCorrect = new JLabel("Length correct: ", JLabel.CENTER);

        disableDecode();

        final int milliSec = 1000;
        final int buffer = 6;
        final int sec = 5+buffer;

        timer = new Timer(1 * milliSec, new ActionListener() {
            long endTime = (new Date().getTime() + sec*milliSec);

            public void actionPerformed(ActionEvent e) {
                long diff = (endTime - new Date().getTime())/1000;
                if (diff <= 0) {
                    System.out.println("Time's up, you lost!");
                    System.exit(-1);
                }
                countDown.setText("Time remaining: " + diff+"                    ");
                countDown.repaint();
            }
        });



//        panelSouth.add(placedCorrectly, BorderLayout.WEST);
//        panelSouth.add(charsCorrect, BorderLayout.CENTER);
//        panelSouth.add(cheatResult, BorderLayout.EAST);
//        panelSouth.add(lengthCorrect, BorderLayout.SOUTH);
        gridText.add(placedCorrectly);
        gridText.add(charsCorrect);
        gridText.add(cheatResult);
        gridText.add(lengthCorrect);

        gridText.setLayout(new GridLayout(5,3));
        gridText.setSize(30,100);
//        gridNorth.setVisible(true);

        panelSouth.add (gridText, BorderLayout.NORTH);



//        panelNorth.add(submitButton, BorderLayout.EAST);

//        panelCenter.add(panelNorth, BorderLayout.NORTH);

       pane.add(panelNorth,BorderLayout.NORTH);
//        pane.add(panelCenter, BorderLayout.NORTH);
        pane.add(panelSouth, BorderLayout.SOUTH);
    }

    private static TimerTask createTimerTask() {
        TimerTask task = new TimerTask()
        {
            public void run()
            {
                System.out.println( "Time's up, you lose! Tough luck" );
                System.exit( -1 );
            }
        };

        return task;
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
//
//        while (true) {
//            long diff = (endTime - new Date().getTime())/1000;
//            System.out.println(diff);
//        }

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

//            Date time = new Date();
//            long startTime = time.getTime();
//            final long endTime = startTime + sec*milliSec;
//            final TimerTask task = createTimerTask();
//            timer = new Timer();
//            timer.schedule(task, sec*milliSec);
//            long diff = (endTime - new Date().getTime())/1000;
//            countDown.setText("Time left: " + diff);
//            countDown.updateUI();

        }

        private void startDecode() {
            startButton.setVisible(false);
            submitButton.setEnabled(true);
            cheatButton.setEnabled(true);
            inputPassword.setEnabled(true);
        }
    }
//
//     Event handler for Solve button */
    private static class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (inputPassword.getPassword() != null) {
                System.out.println("real password: " + genPass.getPassword());
                char[] input = inputPassword.getPassword();
                if (codeChecker.checkPassword(input)) {
                    submitButton.setEnabled(false);
                    System.exit(-1);
                } else {

                    float percentLengthCorrect = codeChecker.percentageCorrectLength(input);
                    lengthCorrect.setText(percentLengthCorrect > -1 ? "Your input length is " + percentLengthCorrect +"% correct." :
                            "Your length is too long");
                    if (codeChecker.inputLengthMatchesPasswordLength(input)) {
                        float percentPlacedCorrect = codeChecker.percentagePlacedCorrectly(input);
                        float percentCorrectInput = codeChecker.percentageCorrectInput(input);
                        placedCorrectly.setText("You have placed "+percentPlacedCorrect+"% of characters correctly!\n");
                        charsCorrect.setText("You have input " + percentCorrectInput+ "% of characters correctly!\n");


//                        placedCorrectly.repaint();
//                        charsCorrect.repaint();
//                        System.out.printf("You have placed %.02f%% characters correctly!\n", percentPlacedCorrect);
//                        System.out.printf("You have input %.02f%% characters correctly!\n", percentCorrectInput);

                    }

                }
            }
        }
    }
//
//    /** Event handler for Reset button */
//    private static class ResetListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            maze.reset();
//        }
//    }

//    /** Worker class for solving the maze */
//    private static class SolverThread extends SwingWorker<Boolean, Object> {
//        @Override
//        public Boolean doInBackground() {
//            return maze.solve();
//        }
//
//        @Override
//        protected void done() {
//            try {
//                if (!get()) {  // test the result of doInBackground()
//                    System.out.println("Maze has no valid solution.");
//                }
//                solveButton.setEnabled(true);
//                resetButton.setEnabled(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
