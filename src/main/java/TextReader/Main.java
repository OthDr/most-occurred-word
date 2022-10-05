package TextReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DOTH
 */
public class Main {

    static boolean isAddedYet(ArrayList<String> words, String word) {
        return words.contains(word);
    }

    public static void main(String[] args) throws FileNotFoundException {

        String text;

        ArrayList<String> uniqueWords = new ArrayList<String>();
        ArrayList<String> allWords = new ArrayList<String>();

        HashMap<String, Integer> wordNcount = new HashMap<String, Integer>();

        String fileName = "textFile.txt";

        Path path = Paths.get(fileName);

        FileReader fileReader = new FileReader(path.toString());

        BufferedReader inputBuffer = new BufferedReader(fileReader);

        try {
            String line;
            String[] words;

            while ((line = inputBuffer.readLine()) != null) {
                words = (line.split(" "));

                for (String word : words) {

                    //Filtering ad ignoring ponctuation
                    if (word.contains(".")) {
                        word = word.replace(".", "");
                    } else if (word.contains(",")) {
                        word = word.replace(",", "");
                    } else if (word.contains(":")) {
                        word = word.replace(":", "");
                    } else if (word.contains(";")) {
                        word = word.replace(";", "");
                    } else if (word.contains("?")) {
                        word = word.replace("?", "");
                    } else if (word.contains("-")) {
                        word = word.replace("-", "");
                    } else if (word.contains("!")) {
                        word = word.replace("!", "");
                    }
                    
                    if (!word.isBlank()) {// if the word is not an empty String or white space
                        allWords.add(word);

                        //get word frequency int the map otherwise get 0 count
                        int wordFreq = wordNcount.getOrDefault(allWords.get(allWords.size() - 1), 0);

                        wordFreq++;
                        wordNcount.put(word, wordFreq);

                    }

                    
                    // ------------------ just a bonus ------------------------
                    if (!isAddedYet(uniqueWords, word) && !word.isBlank() ) {//only to fill a list of each word in the file in only one frequency
                        uniqueWords.add(word);
                    }
                    // --------------------------------------------------------

                }

            }
            //System.out.println(allWords); // 2583 word

            System.out.println("This text has "+ uniqueWords.size() + "unique words.\n\n\n **********************\t The Result\t********************************************\n "); // 844 unique word
            
            String mostOccuredWord = ""; // init of an int with a minimum value
            int maxOccurence = Integer.MIN_VALUE;
            for (String word : wordNcount.keySet()) {
                //System.out.println(word + "=>" + wordNcount.get(word));
                if( wordNcount.get(word) > maxOccurence){
                    maxOccurence = wordNcount.get(word);
                    mostOccuredWord = word;
                }
            }
            
            System.out.println("\t\t The most occured word is: '"+mostOccuredWord+"' ==> "+maxOccurence+" times\n\n ***************************************************************************");
            

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
