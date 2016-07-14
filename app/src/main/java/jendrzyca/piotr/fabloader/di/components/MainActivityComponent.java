package jendrzyca.piotr.fabloader.di.components;

import javax.inject.Singleton;

import dagger.Component;
import jendrzyca.piotr.fabloader.di.modules.AppModule;
import jendrzyca.piotr.fabloader.ui.MainActivity;
import jendrzyca.piotr.fabloader.di.modules.MainActivityModule;
import jendrzyca.piotr.fabloader.di.modules.NetworkModule;
import jendrzyca.piotr.fabloader.di.scopes.PerActivity;

/**
 * Created by huddy on 7/13/16.
 */

@PerActivity
@Component( dependencies = NetworkComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
