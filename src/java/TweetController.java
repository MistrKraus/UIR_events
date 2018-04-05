import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class TweetController {

    private ArrayList<Tweet> tweets = new ArrayList<>();

    private final String CSV_NAME;
    private static final short tweetLength = 4;

    public TweetController(String csvName) {
        this.CSV_NAME = csvName;
    }

    public void loadTweets() {
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("src/resources/tweets.csv")));
            System.out.println("tratrafdafasd");
            while ((line = br.readLine()) != null) {
                tweets.add(new Tweet(splitLine(line), tweetLength));
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while reading CSV file - " + CSV_NAME);
        }
    }

    public void printTweets() {
        for (Tweet t : tweets)
            System.out.println(t.toString());
    }

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
