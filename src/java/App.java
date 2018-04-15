import java.util.Map;

public class App {

    public static TweetController tweetController;

    public static void main(String[] args) {
        String csvName = "src/resources/A16B0065P.csv";

//        String txtt = "text, který obsahuje čárku :O a dvojtečku";
//        String hey = txtt.toUpperCase();
//        System.out.println(hey);

        tweetController = new TweetController(csvName);
        tweetController.loadTweets();

        for (Map.Entry<String, Integer> entry : tweetController.getAllWords().entrySet()) {
            System.out.format("%15s - %d\n", entry.getKey(), entry.getValue());
        }

//        WordController wC = WordController.getInstance();


    }
}
