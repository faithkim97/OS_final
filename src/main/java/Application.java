import java.util.Scanner;

public class Application {
    String password = "";
    public static void main(String[] args) {
        System.out.println("Application started");

        new Application().playGame();

        // Create scanner over user input:

        // ASK FAITH: if we let the user input a password in the game against
        // the AI should we limit it to only dictionary words??
    }

    public void playGame(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Can you guess the password? Input you guess here: ");
        String input = sc.nextLine();

        boolean play = true;
        GeneratePassword genPassword = new GeneratePassword();
        password = genPassword.getPassword();
        CodeCracker cracker = new CodeCracker(password, input);

        //need to change loop condition to be timer
        while (play){
            if (cracker.returnGuess()){
                System.out.println("You cracked the password!");
                play = false;
                continue;
            }
            else {
                cracker.checkInputPassword();
                System.out.println("Hmm you guessed incorrectly... try again: ");
                cracker.setInputPassword(sc.nextLine());
            }
        }

    }

    public String getInputPassword(){
        return password;
    }

}

// CodeCracker class
// while loop: timer > 0: game is playing
//