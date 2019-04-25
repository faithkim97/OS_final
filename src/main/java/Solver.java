import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.TimerTask;
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

/*
    public void createComponents(Container pane) {  

// Create the two panels inside pane and set their layouts
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());       
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());

// Create northButton and put it in place
        JButton northButton = new JButton("North");
        panel1.add(northButton, BorderLayout.NORTH);
        northButton.addActionListener(new directionListener(0,20));

// Create southButton and put it in place
        JButton southButton = new JButton("South");
        panel1.add(southButton, BorderLayout.SOUTH);
        southButton.addActionListener(new directionListener(0,-20));

// Create eastButton and put it in place
        JButton eastButton = new JButton("East");
        panel1.add(eastButton, BorderLayout.EAST);
        eastButton.addActionListener(new directionListener(-20,0));

// Create westButton and put it in place
        JButton westButton = new JButton("West");
        panel1.add(westButton, BorderLayout.WEST);
        westButton.addActionListener(new directionListener(20,0));

// Create zoomInButton and put it in place
        JButton zoomInButton = new JButton("Zoom in");
        panel2.add(zoomInButton, BorderLayout.SOUTH);
        zoomInButton.addActionListener(new zoomListener(1));

// Create zoomOutButton and put it in place
        JButton zoomOutButton = new JButton("Zoom out");
        panel2.add(zoomOutButton, BorderLayout.NORTH);
        zoomOutButton.addActionListener(new zoomListener(-1));

// Set the layout of pane, add the view of map, the MouseListener and the two panels to the pane
        pane.setLayout(new FlowLayout());
        pane.add(view);
        view.addMouseListener(new clickListener());

        panel1.add(panel2);
        pane.add(panel1);

    }
    */

    public static void createComponents(Container pane) {
        //TODO create JComponent for code cracker
        JPanel panelCenter = new JPanel();
//        JPanel panelCenter = new JPanel(new GridLayout(2, 2));
        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelNorthNorth = new JPanel();
        panelSouth.setLayout(new BorderLayout());
        panelNorth.setLayout(new BorderLayout());
        panelNorthNorth.setLayout(new BorderLayout());
        panelCenter.setLayout(new BorderLayout());

        panelCenter.setPreferredSize(new Dimension(50, 50));

        cheatButton = new JButton("Cheat you weakling");
        cheatButton.addActionListener(new CheatListener());
        panelNorthNorth.add(cheatButton, BorderLayout.WEST);

        title = new JLabel("Guess the password you fool!!");
//        pane.add(title);

        countDown = new JLabel ("Time remaining: ", JLabel.CENTER);
        panelNorthNorth.add(cheatButton, BorderLayout.EAST);

        inputPassword = new JPasswordField( 30);
        inputPassword.setPreferredSize(new Dimension(50,50));
        panelNorth.add(panelNorthNorth, BorderLayout.NORTH);
        panelNorth.add(inputPassword, BorderLayout.CENTER);
//        System.out.printf("You have placed %.02f%% characters correctly!\n", percentPlacedCorrect);
//                        System.out.printf("You have input %.02f%% characters correctly!\n", percentCorrectInput);

        placedCorrectly  = new JLabel("Placed Correctly", JLabel.CENTER);
        charsCorrect  = new JLabel("Chars correct:", JLabel.CENTER);
        cheatResult = new JLabel("Cheat Result:", JLabel.CENTER);
        lengthCorrect = new JLabel("Length correct: ", JLabel.CENTER);


        panelSouth.add(placedCorrectly, BorderLayout.WEST);
        panelSouth.add(charsCorrect, BorderLayout.CENTER);
        panelSouth.add(cheatResult, BorderLayout.EAST);
        panelSouth.add(lengthCorrect, BorderLayout.SOUTH);

        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100,100));
        submitButton.addActionListener(new SubmitListener());
        panelNorth.add(submitButton, BorderLayout.EAST);

//        panelCenter.add(panelNorth, BorderLayout.NORTH);

       pane.add(panelNorth,BorderLayout.NORTH);
//        pane.add(panelCenter, BorderLayout.NORTH);
        pane.add(panelSouth, BorderLayout.CENTER);
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

    // Application starts here
    public static void main(String[] args) {
        long sec = 60*1000;
        genPass = new GenPass();
        codeChecker = new CodeChecker(genPass.getPassword());
        Date time = new Date();
        long startTime = time.getTime();
        final long endTime = startTime + sec;
        timer = new Timer(60*1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long diff = (endTime - new Date().getTime());
                countDown.setText(diff +"");
            }
        });

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
                    lengthCorrect.setText(percentLengthCorrect > -1 ? "You are " + percentLengthCorrect +"% correct." :
                            "Your length is too long");
                    if (codeChecker.inputLengthMatchesPasswordLength(input)) {
                        float percentPlacedCorrect = codeChecker.percentagePlacedCorrectly(input);
                        float percentCorrectInput = codeChecker.percentageCorrectInput(input);
                        placedCorrectly.setText("You have placed "+percentPlacedCorrect+"% of characters correctly!\n");
                        charsCorrect.setText("You have input " + percentCorrectInput+ "% of characters correctly!\n");
                        placedCorrectly.repaint();
                        charsCorrect.repaint();
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
