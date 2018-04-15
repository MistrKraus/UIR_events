import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Tweet {
    private int lastProcessedCharId = 0;
    private int temp;

    /**Číslo  tweetu*/
    private final String NO;
    //    private final String LANG;
//    /**Jazyk tweetu*/
    /**Datum a čas zveřejnění tweetu*/
    private final GregorianCalendar DATE;
    /**Neupravený text tweetu*/
    private final String RAW_TXT;
    /**Slova s výskyty*/
    private final Map<String, Integer> BAG_OF_WORDS;

    /**Délka čísla tweetu*/
    private static final short no_lenght = 18;
    /**Délka data zveřejnění tweetu*/
    private static final short date_lenght = 29;

    /***
     * Konstruktor tweetu
     *
     * @param tweet String s interním uložením tweetu
     */
    public Tweet(String tweet) {
        this.NO = processNo(tweet);
//        System.out.println(this.NO);
        this.DATE = processDate(tweet);
//        System.out.println(this.DATE);
        this.RAW_TXT = processText(tweet);
//        System.out.println(this.RAW_TXT);
        this.BAG_OF_WORDS = new HashMap<>();

        for (String s : RAW_TXT.split(" ")) {
            if (BAG_OF_WORDS.containsKey(s))
                BAG_OF_WORDS.put(s, BAG_OF_WORDS.get(s) + 1);
            else
                BAG_OF_WORDS.put(s, 1);
        }
    }

    /**
     * Z tweetu vybere jeho cislo
     *
     * @param tweet String řádku tweetu
     * @return číslo tweetu
     */
    private String processNo(String tweet) {
        lastProcessedCharId = no_lenght;
        while (tweet.charAt(lastProcessedCharId) != ';')
            lastProcessedCharId++;

        return tweet.substring(lastProcessedCharId - no_lenght, lastProcessedCharId);
//
//        int x = lastProcessedCharId;
//        if (tweet.charAt(no_lenght) == ';') {
//            lastProcessedCharId = no_lenght;
//            return tweet.substring(x, no_lenght - 1);
//        } else {
//
//        }
    }

    private GregorianCalendar processDate(String tweet) {
        // finds index x where date starts
        lastProcessedCharId += date_lenght + 3;
        // finds interval where date probably is
        while (!(tweet.charAt(lastProcessedCharId - date_lenght) == ';' && tweet.charAt(lastProcessedCharId) == ';') && tweet.length() < lastProcessedCharId)
            lastProcessedCharId++;

//        System.out.println(tweet.charAt(lastProcessedCharId));

        // chyba
        if (tweet.length() == lastProcessedCharId) {
            System.err.println("Unsupported number format!");
            return null;
        }

        // chyba
        for (int i = 1; i < 5; i++) {
            if (!Character.isDigit(tweet.charAt(lastProcessedCharId - i))) {
                System.err.println("Unsupported number format!");
                return null;
            }
        }

//        lastProcessedCharId--;

//        System.out.println(tweet.substring(lastProcessedCharId - date_lenght + 1, lastProcessedCharId));
        String[] dateStrings = tweet.substring(lastProcessedCharId - date_lenght + 1, lastProcessedCharId).split(" ");
//        System.out.println(Arrays.toString(dateStrings));

//        lastProcessedCharId++;

        // chyba
        if (dateStrings.length != 6) {
            System.err.println("Unsupported date format!");
            return null;
        }

        String[] timeStrings = dateStrings[3].split(":");

        if (timeStrings.length == 3) {
//            System.out.println(Arrays.toString(timeStrings));
            return new GregorianCalendar(Integer.parseInt(dateStrings[5]), getMonthNo(dateStrings[1]), Integer.parseInt(dateStrings[2])
                    , Integer.parseInt(timeStrings[0]), Integer.parseInt(timeStrings[1]), Integer.parseInt(timeStrings[2]));
        } else {
            System.err.println("Unsupported time format!");
            return null;
        }
    }

    private String processText(String tweet) {
        return tweet.substring(lastProcessedCharId + 1, tweet.length() - 1).toLowerCase();
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

    /**
     * Returns all words in tweet
     *
     * @return String array of words in tweet
     */
    public String[] getAllWords() {
        String line = RAW_TXT.replaceAll("[^\\w\\sěščřžýáíéóťňúůöëäüïÿď]", "");

        return line.split(" ");
    }

    /***
     * Vrátí počet výskytů jednotlivých slov v podobě mapy, kde string je klíč a integer počet výskytů
     *
     * @return Mapa Stringů a Integerů
     */
    public Map<String, Integer> getBAG_OF_WORDS() {
        return this.BAG_OF_WORDS;
    }

    @Override
    public String toString() {
        return NO + ';' + DATE.toString() + ": " + RAW_TXT;
    }
}
