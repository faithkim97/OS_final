import java.io.*;
import java.util.Random;

// find a random word in the dictionary
public class GeneratePassword {
    String setPassword;


    public GeneratePassword(){
        setPasswordByDict();
    }

    public void setPasswordByDict() {
        Dictionary dict = new Dictionary();
        Random rand = new Random();
        int randIndex = rand.nextInt(99171);
        setPassword = dict.getWordByIndex(randIndex);
    }

    public String getPassword(){
        return setPassword;
    }
}

