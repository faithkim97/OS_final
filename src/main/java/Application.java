import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Application extends JComponent {

    private String input;

    public static void main(String[] args) {
        System.out.println("Application started");
        Dictionary dic = new Dictionary();
        dic.sortByLength();

        new NewApplication().playGame();

    }

    public void playGame() {

        Timer timer = new Timer();
        Scanner sc = new Scanner(System.in);
        System.out.println("Can you guess the password? Input you guess here: ");
        input = sc.nextLine();

        boolean play = true;
        GenPass password = new GenPass();
        CodeChecker checker = new CodeChecker(password.getPassword());
        TimerTask task = createTimerTask();
        timer.schedule(task, 60 * 1000); //count downs for 60 seconds
        Cheater cheater = new Cheater(checker);
        System.out.println("REAL PASSWORD: " + password.getPassword());
        while (play) {
            if (input.equals("!")) {
                input = cheater.doCheat();
                System.out.println(input);
            }

            if (checker.checkPassword(input.toCharArray())) {
                System.out.println("You cracked the password!");
                play = false;
                continue;

            } else {
                System.out.println("Hmm you guessed incorrectly... try again: ");
                float percentLengthCorrect = checker.percentageCorrectLength(input.toCharArray());
                System.out.printf("Percentage of input length: %.02f%%\n", percentLengthCorrect);
                if (checker.inputLengthMatchesPasswordLength(input.toCharArray())) {
                    float percentPlacedCorrect = checker.percentagePlacedCorrectly(input.toCharArray());
                    float percentCorrectInput = checker.percentageCorrectInput(input.toCharArray());
                    System.out.printf("You have placed %.02f%% characters correctly!\n", percentPlacedCorrect);
                    System.out.printf("You have input %.02f%% characters correctly!\n", percentCorrectInput);

                }

            }
            input = sc.nextLine();
        }
    }


    private TimerTask createTimerTask() {
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

    public void paintComponent (Graphics g){
    }
}//endclass