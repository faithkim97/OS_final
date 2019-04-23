import java.io.*;
import java.util.Arrays;


public class Dictionary {
    private static final int SIZE = 99172;
    private String[] dictionary = new String[SIZE];

    public Dictionary(){
        createDict();
    }

    public void createDict() {
        try{
            BufferedReader readDictionary = new BufferedReader(new FileReader(new File("/usr/share/dict/words")));
            int index = 0;

            try {
                String currentLine = readDictionary.readLine();
                while (currentLine != null && index < SIZE) {
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
        if (index >= 0 && index < SIZE) {
            return dictionary[index];
        }
        return "";

    }

    public int getLength() { return SIZE; }

    public void sortByLength() {
        Arrays.sort(dictionary, new java.util.Comparator<String>() {
            public int compare(String s1, String s2) {
                // TODO: Argument validation (nullity, length)
                return s1.length() - s2.length();// comparision
            }
        });
    }



}