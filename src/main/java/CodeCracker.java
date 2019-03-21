// This checks the characters in the input against the assigned password
//TODO refactor as CodeChecker
public class CodeCracker {
    private char[] inputPassword;
    private char[] setPassword;


    public CodeCracker(String setPassword) {
        this.setPassword = setPassword.toCharArray();
        inputPassword = new char[0];
    }

    public CodeCracker(String setPassword, String userInput){
        this.inputPassword =  userInput.toCharArray();
        this.setPassword = setPassword.toCharArray();
    }

    public boolean returnGuess(){
        return inputPassword.equals(setPassword);
    }


    public void checkInputPassword(){
            if (!equalLength()) {
                System.out.println("Incorrect input password length.");
            }
            System.out.printf("You have placed %.02f%% characters correctly!\n",percentagePlacedCorrectly());
                // call percentage letters correct
            System.out.printf("You have input %.02f%% characters correctly!\n",percentageCorrectInput());

    }

    public boolean equalLength() {
        return (inputPassword.length == setPassword.length);
    }

    public String compareLength(){
        if (inputPassword.length < setPassword.length){
            return "short";
        }
        else if (inputPassword.length > setPassword.length){
            return "long";
        }
        else{
            return "same";
        }
    }

    public float percentagePlacedCorrectly(){
        int counterSame = 0;
        if (equalLength()) {
            for (int i = 0; i < inputPassword.length; i++){
                if (inputPassword[i] == setPassword[i]){
                    counterSame ++;
                }
            }
        }//endif

        return (counterSame/inputPassword.length)*100.00f;
    }

    public float percentageCorrectInput(){
        int counterSame = 0;
        char[] passwordArr = new char[256];

        for (int i = 0; i < setPassword.length; i++) {
            passwordArr[setPassword[i]]++;
        }

        for (int i = 0; i < inputPassword.length; i++) {
            if (passwordArr[inputPassword[i]] > 0){
                passwordArr[inputPassword[i]] --;
                counterSame ++;
            }
        }

        return (counterSame/inputPassword.length)*100.00f;
    }


    public void setInputPassword(String newInput){
        this.inputPassword = newInput.toCharArray();
    }


}
