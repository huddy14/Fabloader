package jendrzyca.piotr.fabloader.network;

import jendrzyca.piotr.fabloader.model.converter.SongDownload;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by huddy on 7/13/16.
 */
public interface ConverterApi {

    @GET("/fetch")
    Observable<SongDownload>getSong(@Query("format") String format , @Query("video") String video);
}
