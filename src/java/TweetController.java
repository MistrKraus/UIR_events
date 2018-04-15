import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Kontroluje a pracuje s tweety
 */
public class TweetController {

    /**ArrayList Tweetů*/
    private ArrayList<Tweet> tweets = new ArrayList<>();

    /**Název csv souboru*/
    private final String CSV_NAME;
    /**Počet sloupců v csv*/
    private static final short tweetLength = 4;

    /***
     * Konstruktor s parametrem cesty k csv souboru s tweety
     *
     * @param csvName cesta k csv souboru s tweety
     */
    public TweetController(String csvName) {
        this.CSV_NAME = csvName;
    }

    /**Načte tweety z csv do Arraylistu*/
    public void loadTweets() {
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(this.CSV_NAME)));
            while ((line = br.readLine()) != null) {
                tweets.add(new Tweet(line));
                //System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while reading CSV file - " + CSV_NAME);
        }
    }

    /***
     * Vypíše tweety
     */
    public void printTweets() {
        for (Tweet t : tweets)
            System.out.println(t.toString());
    }

    /**
     * Vrátí arrayList všech slov
     *
     * @return arrayList Stringů se všemi slovy
     */
    public Map<String, Integer> getAllWords() {
//        ArrayList<String> allWords = new ArrayList<>();
        Map<String, Integer> bagOfWords = new HashMap();

        for (Tweet t : tweets) {
            for (String s : t.getAllWords()) {
                if (bagOfWords.containsKey(s))
                    bagOfWords.put(s, bagOfWords.get(s) + 1);
                else
                    bagOfWords.put(s, 1);
            }
        }

        return bagOfWords;
    }

    /***
     * Rozdělí řádku tweetu předávanou v parametru na jednotlivé sloupce
     *
     * @param line řádek s tweetem
     * @return Stringové pole
     */
    private String[] splitLine(String line) {
        String splitString = ";";
        String[] result = line.split(splitString);
        int resultLength = result.length;

        if (resultLength > tweetLength) {
            for (int i = tweetLength; i < resultLength; i++)
                result[tweetLength - 1] += result[i];

            String[] newResult = new String[tweetLength];
            for (int i = 0; i < tweetLength; i++)
                newResult[i] = result[i];

            return newResult;
        }

        return result;
    }
}
