import java.util.Arrays;

// This checks the characters in the input against the assigned password
public class CodeChecker {
    private char[] setPassword;


    public CodeChecker(String setPassword) {
        this.setPassword = setPassword.toCharArray();
    }

    public CodeChecker(String setPassword, String userInput){
        this.setPassword = setPassword.toCharArray();
    }

    public boolean checkPassword(char[] inputPassword) {
        boolean check = Arrays.equals(inputPassword, setPassword);
        return check;
    }


//    public void checkInputPassword(){
//            if (!inputLengthMatchesPasswordLength()) {
//                System.out.println("Incorrect input password length.");
//            }
//            System.out.printf("You have placed %.02f%% characters correctly!\n",percentagePlacedCorrectly());
//                // call percentage letters correct
//            System.out.printf("You have input %.02f%% characters correctly!\n",percentageCorrectInput());
//
//    }

    public float percentageCorrectLength(char[] inputPassword) {

        float percentLen =  ((float) inputPassword.length/(float)setPassword.length)*100.00f;
        return (percentLen > 100.00f ? -1.0f : percentLen);

    }

    public boolean inputLengthMatchesPasswordLength(char[] inputPassword) {
        return (inputPassword.length == setPassword.length);
    }
//
//    public String compareLength(){
//        if (inputPassword.length < setPassword.length){
//            return "short";
//        }
//        else if (inputPassword.length > setPassword.length){
//            return "long";
//        }
//        else{
//            return "same";
//        }
//    }

    public float percentagePlacedCorrectly(char[] inputPassword){
        int counterSame = 0;
        if (inputLengthMatchesPasswordLength(inputPassword)) {
            for (int i = 0; i < inputPassword.length; i++){
                if (inputPassword[i] == setPassword[i]){
                    counterSame ++;
                }
            }
        }

        float percentRight = ((float)counterSame/(float)setPassword.length)*100.00f;
        return percentRight;
    }

    public float percentageCorrectInput(char[] inputPassword){
        int counterSame = 0;
        char[] passwordArr = new char[256];

        for (int i = 0; i < setPassword.length; i++) {
            passwordArr[setPassword[i]]++;
        }

        for (int i = 0; i < inputPassword.length; i++) {
            if (passwordArr[inputPassword[i]] > 0){
                passwordArr[inputPassword[i]]--;
                counterSame++;
            }
        }

        float percentRight = ((float)counterSame/(float)setPassword.length)*100.00f;
        return percentRight;
    }

}