import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Cheater {
    int maxPercent;
    Dictionary dictionary;
    CodeChecker checker;
    Set<String> seen;

    //TODO pass in code cracker and not the actual password
    public Cheater(CodeChecker checker) {
        seen = new HashSet<String>();
        dictionary = new Dictionary();
        this.checker = checker;
        dictionary.sortByLength();

    }


    private String findWordByLength(int min, int max) {
        String[] dict = dictionary.getDictionary();
        for (int i = 0; i < dict.length;i++) {
            String currWord = dict[i];
            if (currWord.length() <= max && currWord.length() >= min) {
                return currWord;
            }
        }
        return "";

    }

    private String findWordByLength(int len) {
        String[] dict = dictionary.getDictionary();
        for (int i = 0; i <  dict.length; i++) {
            String currWord = dict[i];
            if (currWord.length() == len && !seen.contains(currWord)) {
                seen.add(currWord);
                return currWord;
            }
        }

        return "";
    }






    public String doCheat() {
        //pick a word from the dictionary
        String picked = dictionary.getWordByIndex(new Random().nextInt(dictionary.getLength()));
        char[] pickedArr = picked.toCharArray();
        while (!checker.inputLengthMatchesPasswordLength(pickedArr)) {
            //check length
            if(!checker.inputLengthMatchesPasswordLength(pickedArr)) {
                float percentLen =  checker.percentageCorrectLength(pickedArr);
                if (percentLen == -1.0f) {
                    pickedArr = findWordByLength(1, pickedArr.length - 1).toCharArray();
                } else if ( percentLen < 100.0f) {
                    pickedArr = findWordByLength(pickedArr.length + 1, 24).toCharArray(); //24 == max length in dictionary
                }
            }
        }//endwhile

        while (checker.percentageCorrectInput(pickedArr) != 100.0f) {
            picked = findWordByLength(pickedArr.length);
            pickedArr  = picked.toCharArray();
        }

        return picked;
    }
}