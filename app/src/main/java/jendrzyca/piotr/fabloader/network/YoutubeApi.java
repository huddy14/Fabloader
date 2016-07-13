package jendrzyca.piotr.fabloader.network;

import com.google.api.services.youtube.YouTube;


import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by huddy on 7/13/16.
 */
public interface YoutubeApi {
    @GET("/youtube/v3/search")
    Observable<YouTube.Search.List> searchSongs(@Query("part") String part, @Query("maxResults") String maxResults ,
                                                @Query("q") String query, @Query("key") String key);
}
