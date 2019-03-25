import java.util.Random;

public class Cheat {
    int maxPercent;
    Dictionary dictionary;
    CodeCracker cracker;

    //TODO pass in code cracker and not the actual password
    public Cheat(CodeCracker cracker){
        dictionary = new Dictionary();
        this.cracker = cracker;
    }

    public void tryGuesses(){
        int guessLength = Integer.MIN_VALUE;
        Random rand = new Random();
        String inputPassword = dictionary.getWordByIndex(rand.nextInt(99172));
        cracker.setInputPassword(inputPassword);






    }
}
