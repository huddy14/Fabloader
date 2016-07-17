package jendrzyca.piotr.fabloader.di.components;

import android.app.DownloadManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import jendrzyca.piotr.fabloader.di.modules.AppModule;
import jendrzyca.piotr.fabloader.di.modules.NetworkModule;
import jendrzyca.piotr.fabloader.di.scopes.PerActivity;
import retrofit2.Retrofit;

/**
 * Created by huddy on 7/13/16.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface NetworkComponent {

    @Named("RetrofitYoutube")
    Retrofit retrofitYoutube();

    @Named("RetrofitDownloader")
    Retrofit retrofitDownloader();

    @Named("RetrofitVideoDetails")
    Retrofit retrofitVideoDetails();

    DownloadManager downloadManager();
}
