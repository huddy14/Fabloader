package jendrzyca.piotr.fabloader.network;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;


import java.util.List;

import jendrzyca.piotr.fabloader.model.youtube.YoutubeRespones;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by huddy on 7/13/16.
 */
public interface YoutubeApi {
    @GET("/youtube/v3/search")
    Observable<YoutubeRespones> searchSongs(@Query("part") String part, @Query("maxResults") String maxResults ,
                                            @Query("q") String query, @Query("type") String type, @Query("key") String key);
}
