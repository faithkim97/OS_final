import java.awt.*;
import java.awt.event.*;
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
    private static JLabel lengthCorrect;
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
//        pane.add(codeCrackerApp);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        solveButton = new JButton("Solve");
//        resetButton.addActionListener(new ResetListener());


        pane.add(panel, BorderLayout.NORTH);
        cheatButton = new JButton("Cheat you weakling");
        pane.add(cheatButton, BorderLayout.EAST);

        title = new JLabel("Guess the password you fool!!");
        pane.add(title, BorderLayout.NORTH);

        inputPassword = new JPasswordField( 30);
        pane.add(inputPassword, BorderLayout.NORTH);
//        System.out.printf("You have placed %.02f%% characters correctly!\n", percentPlacedCorrect);
//                        System.out.printf("You have input %.02f%% characters correctly!\n", percentCorrectInput);

        placedCorrectly  = new JLabel(" ", JLabel.CENTER);
        charsCorrect  = new JLabel(" ", JLabel.CENTER);

        pane.add(placedCorrectly);
        pane.add(charsCorrect);

        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(50,50));
        submitButton.addActionListener(new SubmitListener());
        pane.add(submitButton);


        pane.add(panel,BorderLayout.SOUTH);
    }

    // Application starts here
    public static void main(String[] args) {
//        if (args.length == 0) {
//            maze = new Maze();
//        } else {
//            maze = new Maze(args[0]);
//        }

        genPass = new GenPass();
        codeChecker = new CodeChecker(genPass.getPassword());


        //new NewApplication().playGame();
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
//
//     Event handler for Solve button */
    private static class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (inputPassword.getPassword() != null) {
                System.out.println("real password: " + genPass.getPassword());
                char[] input = inputPassword.getPassword();
                if (codeChecker.checkPassword(input)) {
                    System.out.println("You cracked the password!");
                    submitButton.setEnabled(false);
                    System.exit(-1);
                } else {

                    System.out.println("Hmm you guessed incorrectly... try again: ");
                    float percentLengthCorrect = codeChecker.percentageCorrectLength(input);
                    System.out.printf("Percentage of input length: %.02f%%\n", percentLengthCorrect);
                    if (codeChecker.inputLengthMatchesPasswordLength(input)) {
                        float percentPlacedCorrect = codeChecker.percentagePlacedCorrectly(input);
                        float percentCorrectInput = codeChecker.percentageCorrectInput(input);
                        placedCorrectly.setText("You have placed "+percentPlacedCorrect+" characters correctly!\n");
                        charsCorrect.setText("You have input " + percentCorrectInput+ " characters correctly!\n");
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
