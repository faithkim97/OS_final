import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Application {
    public static void main(String[] args) {
        System.out.println("Application started");

        new Application().playGame();

    }

    public void playGame() {

        Timer timer = new Timer();
        Scanner sc = new Scanner(System.in);
        System.out.println("Can you guess the password? Input you guess here: ");
        String input = sc.nextLine();

        boolean play = true;
        GeneratePassword password = new GeneratePassword();
        CodeChecker checker = new CodeChecker(password.getPassword(), input);
        TimerTask task = createTimerTask();
        timer.schedule(task, 5 * 1000); //count downs for 60 seconds
//        long timeIntervalInSeconds = (task.scheduledExecutionTime() - System.currentTimeMillis())/1000;

        while (play) {
            if (checker.checkPassword()) {
                System.out.println("You cracked the password!");
                play = false;
                continue;
            } else {
//                checker.checkInputPassword();
                System.out.println("Hmm you guessed incorrectly... try again: ");
                float percentLengthCorrect = checker.percentageCorrectLength();
                System.out.printf("Percentage of input length: %.02f%%\n", percentLengthCorrect);
                if (checker.inputLengthMatchesPasswordLength()) {
                    float percentPlacedCorrect = checker.percentagePlacedCorrectly();
                    float percentCorrectInput = checker.percentageCorrectInput();
                    System.out.printf("You have placed %.02f%% characters correctly!\n", percentPlacedCorrect);
                    System.out.printf("You have input %.02f%% characters correctly!\n", percentCorrectInput);

                }

            }
            checker.setInputPassword(sc.nextLine());
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
}//endclass


