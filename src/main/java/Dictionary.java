import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
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

    public int getLength() { return dictionary.length; }

    public void sortByLength() {
        Arrays.sort(dictionary, new java.util.Comparator<String>() {
            public int compare(String s1, String s2) {
                // TODO: Argument validation (nullity, length)
                return s1.length() - s2.length();// comparision
            }
        });
    }



}