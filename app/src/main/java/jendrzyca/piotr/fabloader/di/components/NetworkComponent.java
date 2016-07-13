package jendrzyca.piotr.fabloader.di.components;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import jendrzyca.piotr.fabloader.di.modules.AppModule;
import jendrzyca.piotr.fabloader.di.modules.NetworkModule;
import retrofit2.Retrofit;

/**
 * Created by huddy on 7/13/16.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface NetworkComponent {

    @Named("RetrofitYoutube")
    Retrofit RetrofitYoutube();

    @Named("RetrofitConventer")
    Retrofit RetrofitConventer();
}
