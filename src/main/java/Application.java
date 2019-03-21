import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("Application started");

        playGame ();

        // Create scanner over user input:

        // ASK FAITH: if we let the user input a password in the game against
        // the AI should we limit it to only dictionary words??
    }

    public static void playGame(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Can you guess the password? Input you guess here: ");
        String input = sc.nextLine();

        boolean play = true;
        GeneratePassword genPassword = new GeneratePassword();
        String password = genPassword.getPassword();
        CodeCracker cracker = new CodeCracker(password, input);

        while (play){
            if (cracker.returnGuess()){
                System.out.println("You cracked the password!");
                play = false;
                continue;
            }
            else {
                System.out.println("Hmm you guessed incorrectly... try again: ");
                cracker.setInputPassword(sc.nextLine());
            }
        }

    }

}

// CodeCracker class
// while loop: timer > 0: game is playing
//