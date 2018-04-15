public class WordController {
    private static WordController instance = null;
//    private static

    protected WordController() {
        // Exists only to defeat instantiation.
    }

    public static WordController getInstance() {
        if(instance == null) {
            instance = new WordController();
        }
        return instance;
    }


}
