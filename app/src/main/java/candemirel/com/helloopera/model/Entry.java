package candemirel.com.helloopera.model;

public class Entry {

    private final static String BASE_URL = "https://www.reddit.com/";

    private String id;
    private String title;
    private String subreddit;
    private int score;
    private String permalink;

    public String getId() {
        return id;
    }

    public String getTitle() {

        if (title == null) {
            return "[untitled]";
        } else {
            return title;
        }
    }

    public String getSubreddit() {
        return subreddit;
    }

    public int getScore() {
        return score;
    }

    public String getUrl() {
        return BASE_URL + permalink;
    }
}

