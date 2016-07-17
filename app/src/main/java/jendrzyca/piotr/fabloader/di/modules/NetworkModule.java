package jendrzyca.piotr.fabloader.di.modules;

import android.app.Application;
import android.app.DownloadManager;
import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jendrzyca.piotr.fabloader.model.youtube.search_list.ItemList;
import jendrzyca.piotr.fabloader.model.youtube.video_details.StatisticsList;
import jendrzyca.piotr.fabloader.model.youtube_dl.Formats;
import jendrzyca.piotr.fabloader.utils.SearchDeserializer;
import jendrzyca.piotr.fabloader.utils.StatisticsDeserialzer;
import jendrzyca.piotr.fabloader.utils.YoutubeDlDeserializer;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huddy on 7/13/16.
 */
@Module
public class NetworkModule {

    private final String BASE_URL_YOUTUBE = "https://www.googleapis.com";
    private final String BASE_URL_YOUTUBE_DOWNLOAD = "http://youtube-dl.appspot.com";

    public NetworkModule() {
    }

    /**
     * Declaration of cache size for our OkHttp client
     * @param application
     * @return
     */
    @Provides
    @Singleton

    public Cache provideCache(Application application) {
        int size = 10 * 1024 * 1024; //10 Mb
        Cache cache = new Cache(application.getCacheDir(), size);
        return cache;
    }

    /**
     * OkHttp clients singleton
     * @param cache
     * @return
     */
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        return okHttpClient;
    }

    /**
     * Converter for download API response
     * @return
     */
    @Provides
    @Singleton
    @Named("GsonDownloader")
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Formats.class, new YoutubeDlDeserializer());

        return gsonBuilder.create();
    }

    /**
     * Converter for /youtube/v3/videos response
     * @return
     */
    @Provides
    @Singleton
    @Named("GsonVideoContent")
    public Gson provideGsonForVideoContent() {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(StatisticsList.class, new StatisticsDeserialzer());

        return gsonBuilder.create();
    }


    /**
     * Converter for /youtube/v3/search API response
     * @return
     */
    @Provides
    @Singleton
    @Named("GsonYoutubeSearch")
    public Gson provideGsonForYoutubeSearch() {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(ItemList.class, new SearchDeserializer());

        return gsonBuilder.create();
    }

    /**
     * Retrofit instance for handling search requests
     * @param gson YoutubeSearch instance
     * @param client
     * @return
     */
    //// TODO: 7/17/16 try to have only one instance of retrofityoutube with multiple converters
    @Provides
    @Singleton
    @Named("RetrofitYoutube")
    public Retrofit provideRetrofitYoutube(@Named("GsonYoutubeSearch") Gson gson, OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL_YOUTUBE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;

    }

    /**
     * Retrofit instance for handling video details requests
     * @param gson VideoContent instance
     * @param client
     * @return
     */
    @Provides
    @Singleton
    @Named("RetrofitVideoDetails")
    public Retrofit provideRetrofitVideoDetails(@Named("GsonVideoContent") Gson gson, OkHttpClient client)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL_YOUTUBE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

    /**
     * Retrofit instance for handling song downloads requests
     * @param gson Downloader instance
     * @param client
     * @return
     */
    @Provides
    @Singleton
    @Named("RetrofitDownloader")
    public Retrofit provideRetrofitDownloader(@Named("GsonDownloader") Gson gson, OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL_YOUTUBE_DOWNLOAD)
                .client(client)
                .build();
        return retrofit;
    }

    /**
     * Androids DownloadManager singleton instance
     * @param application
     * @return
     */
    @Provides
    @Singleton
    public DownloadManager provideDownloadManager(Application application)
    {
        return (DownloadManager) application.getSystemService(Context.DOWNLOAD_SERVICE);
    }

}
