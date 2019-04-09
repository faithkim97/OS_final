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

    /** Solve button */
    private static JButton solveButton;


    private static JButton cheatButton;

    private static JPasswordField inputPassword;

    private static JLabel title;

    private static JButton submit;

    private static NewApplication codeCrackerApp;



    public static void createAndShowGUI() {
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        frame = new JFrame("Crack the Code!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add components
        createComponents(frame.getContentPane());

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void createComponents(Container pane) {

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        solveButton = new JButton("Solve");
//        resetButton.addActionListener(new ResetListener());


        pane.add(panel, BorderLayout.NORTH);
        cheatButton = new JButton("Cheat you weakling");
        panel.add(cheatButton);

        title = new JLabel("Guess the password you fool!!");
        panel.add(title);

        inputPassword = new JPasswordField( 30);
        panel.add(inputPassword);



        submit = new JButton("Submit");
        panel.add(submit);

        pane.add(panel,BorderLayout.SOUTH);
    }

    // Application starts here
    public static void main(String[] args) {
//        if (args.length == 0) {
//            maze = new Maze();
//        } else {
//            maze = new Maze(args[0]);
//        }

//        codeCrackerApp = new NewApplication();

        new NewApplication().playGame();
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
//    /** Event handler for Solve button */
//    private static class SolveListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            maze.reset();
//            // call to solve should be on a new thread
//            solveButton.setEnabled(false);
//            resetButton.setEnabled(false);
//            (new SolverThread()).execute();
//        }
//    }
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
