import java.util.Random;

public class Cheat {
    int maxPercent;
    Dictionary dictionary;
    CodeChecker checker;

    //TODO pass in code cracker and not the actual password
    public Cheat(CodeChecker checker){
        dictionary = new Dictionary();
        this.checker = checker;
    }

    public void tryGuesses(){
        int guessLength = Integer.MIN_VALUE;
        Random rand = new Random();
        String inputPassword = dictionary.getWordByIndex(rand.nextInt(99172));
        checker.setInputPassword(inputPassword);






    }
}
