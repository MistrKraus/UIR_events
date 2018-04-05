public class App {

    public static TweetController tweetController;

    public static void main(String[] args) {
        String csvName = "src/resources/tweets.csv";

        tweetController = new TweetController(csvName);
        tweetController.loadTweets();
        tweetController.printTweets();
    }
}
