package candemirel.com.helloopera;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit API client for Reddit
 */
class RedditAPIFactory {

    private static final String BASE_URL = "http://www.reddit.com/";
    private static Retrofit retrofit = null;
    static final int POST_COUNT = 50;

    /**
     * Creates Reddit api to fetch data from server
     * @return RedditApi
     */
     static RedditApi createAPI() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(RedditApi.class);
    }
}
