import java.util.Date;

public class Tweet {

    private final String NO;
    private final String LANG;
    private final Date DATE;
    private final String RAW_TXT;

    public Tweet(String[] tweet, short tweetLength) {
        if (tweet.length != tweetLength) {
            this.NO = "-";
            this.LANG = "-";
            this.DATE = null;
            this.RAW_TXT = "-";
            return;
        }

        this.NO = tweet[0];
        this.LANG = tweet[1];
        this.RAW_TXT = tweet[3].replaceAll("(,)++", "");

        String[] dateStrings = tweet[2].split(" ");
        String[] timeStrings = dateStrings[3].split(":");

        if (timeStrings.length == 3) {
            this.DATE = new Date(Integer.parseInt(dateStrings[5]), getMonthNo(dateStrings[1]), Integer.parseInt(dateStrings[2])
                    , Integer.parseInt(timeStrings[0]), Integer.parseInt(timeStrings[1]), Integer.parseInt(timeStrings[2]));
        } else {
            this.DATE = null;
            System.err.println("Unsupported time format!");
        }
    }

    /**
     * Returns month number. If not found return 0.
     *
     * @param month 3 letter month format
     * @return Month number. If not found 0.
     */
    private int getMonthNo(String month) {
        switch (month.toLowerCase()) {
            case "jan":
                return 1;
            case "feb":
                return 2;
            case "mar":
                return 3;
            case "apr":
                return 4;
            case "may":
                return 5;
            case "jun":
                return 6;
            case "jul":
                return 7;
            case "aug":
                return 8;
            case "sep":
                return 9;
            case "oct":
                return 10;
            case "nov":
                return 11;
            case "dec":
                return 12;
            default:
                System.out.println(month);
                return 0;
        }
    }

    @Override
    public String toString() {
        return NO + ';' + LANG + ';' + DATE.toString() + ": " + RAW_TXT;
    }
}
