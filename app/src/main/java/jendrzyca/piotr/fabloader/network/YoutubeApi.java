package jendrzyca.piotr.fabloader.network;

import jendrzyca.piotr.fabloader.model.youtube.search_list.YoutubeRespones;
import jendrzyca.piotr.fabloader.model.youtube.video_details.Statistics;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by huddy on 7/13/16.
 */
public interface YoutubeApi {

    String PART = "snippet";
    String KEY = "AIzaSyA7SIqqWifjEKZwaGJNj73K4ELyKYLvBCM";
    String MAX_RESULTS = "30";
    String TYPE = "video";
    String PART_STATISTICS = "contentDetails,statistics";

    @GET("/youtube/v3/search")
    Observable<YoutubeRespones> searchSongs(@Query("part") String part, @Query("maxResults") String maxResults ,
                                            @Query("q") String query, @Query("type") String type, @Query("key") String key);

    @GET("/youtube/v3/videos")
    Observable<Statistics> videoStatisctics(@Query("part") String part, @Query("id") String id, @Query("key")String key);
}
