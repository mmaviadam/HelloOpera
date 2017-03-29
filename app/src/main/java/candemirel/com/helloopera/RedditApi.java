package candemirel.com.helloopera;

import candemirel.com.helloopera.model.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface of Retrofit api for Reddit
 */
interface RedditApi {
    @GET("/r/gaming/top.json")
    Call<ResponseBody> getRedditItems(@Query("limit") int limit);
}
