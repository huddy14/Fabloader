package jendrzyca.piotr.fabloader;

import android.app.Application;

import jendrzyca.piotr.fabloader.di.components.DaggerNetworkComponent;
import jendrzyca.piotr.fabloader.di.components.NetworkComponent;
import jendrzyca.piotr.fabloader.di.modules.AppModule;
import jendrzyca.piotr.fabloader.di.modules.NetworkModule;


/**
 * Created by huddy on 7/13/16.
 */
public class Fabloader extends Application {

    NetworkComponent networkComponent;
    @Override
    public void onCreate() {
        super.onCreate();

//        networkComponent = DaggerNetworkComponent.builder()
//                .appModule(new AppModule(this))
//                .networkModule(new NetworkModule())
//                .build();
        networkComponent = DaggerNetworkComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();


    }

    public NetworkComponent getNetworkComponent()
    {
        return this.networkComponent;
    }

}
