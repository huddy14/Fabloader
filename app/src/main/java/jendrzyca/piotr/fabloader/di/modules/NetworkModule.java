package jendrzyca.piotr.fabloader.di.modules;

import android.app.Application;
import android.app.DownloadManager;
import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jendrzyca.piotr.fabloader.model.youtube.video_details.Statistics;
import jendrzyca.piotr.fabloader.model.youtube_dl.Formats;
import jendrzyca.piotr.fabloader.utils.StatisticsDeserialzer;
import jendrzyca.piotr.fabloader.utils.YoutubeDlDeserializer;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by huddy on 7/13/16.
 */
@Module
public class NetworkModule {

    private final String BASE_URL_YOUTUBE = "https://www.googleapis.com";
    private final String BASE_URL_CONVERTER = "http://www.youtubeinmp3.com";
    private final String BASE_URL_YOUTUBE_DOWNLOAD = "http://youtube-dl.appspot.com";

    public NetworkModule() {
    }

    @Provides
    @Singleton

    public Cache provideCache(Application application) {
        int size = 10 * 1024 * 1024; //10 Mb
        Cache cache = new Cache(application.getCacheDir(), size);
        return cache;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        return okHttpClient;
    }


    @Provides
    @Singleton
    @Named("GsonDownloader")
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Formats.class, new YoutubeDlDeserializer());

        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    @Named("GsonVideoContent")
    public Gson provideGsonForVideoContent() {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Statistics.class, new StatisticsDeserialzer());

        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    @Named("RetrofitYoutube")
    public Retrofit provideRetrofitYoutube(@Named("GsonVideoContent") Gson gson, OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL_YOUTUBE)
                .client(client)
                //.addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;

    }

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

    @Provides
    @Singleton
    public DownloadManager provideDownloadManager(Application application)
    {
        return (DownloadManager) application.getSystemService(Context.DOWNLOAD_SERVICE);
    }

}
