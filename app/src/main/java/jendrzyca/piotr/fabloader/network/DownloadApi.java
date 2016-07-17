package jendrzyca.piotr.fabloader.network;


import jendrzyca.piotr.fabloader.model.youtube_dl.Formats;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Piotr Jendrzyca on 7/16/16.
 */
public interface DownloadApi {

    String LINK = "https://www.youtube.com/watch?v=";

    @GET("/api/info")
    Observable<Formats> getSong(@Query("url") String url);
}
