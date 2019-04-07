/**import java.io.*;
import java.util.Random;

public class Dictionary {
    private String[] dictionary = new String[99172];

    public Dictionary(){
        createDict();
    }

    public void createDict() {
        try{
            BufferedReader readDictionary = new BufferedReader(new FileReader(new File("/usr/share/dict/words")));
            int index = 0;

            try {
                String currentLine = readDictionary.readLine();
                while (currentLine != null && index < 99172) {
                    dictionary[index] = currentLine;
                    currentLine = readDictionary.readLine();
                    index++;
                }

            }
            catch (IOException e){
                System.err.println("Couldn't read dictionary.");
                System.exit(-1);
            }
        }


        catch (FileNotFoundException e){
            System.err.println("Couldn't read dictionary.");
            System.exit(-1);
        }
    }

    public String[] getDictionary() {
        return dictionary;
    }

    public String getWordByIndex(int index) {
        if (index >= 0 && index < dictionary.length) {
            return dictionary[index];
        }
        return "";

    }
}
*/