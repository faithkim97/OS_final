// This checks the characters in the input against the assigned password

public class CodeCracker {
    private String inputPassword;
    private String setPassword;


    public CodeCracker(String setPassword, String userInput){
        this.inputPassword = inputPassword;
        this.setPassword = setPassword;
    }

    public boolean returnGuess(){
        return inputPassword.equals(setPassword);
    }

    public void setInputPassword(String newInput){
        this.inputPassword = newInput;
    }


}
