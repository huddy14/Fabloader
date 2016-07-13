package jendrzyca.piotr.fabloader.di.components;

import dagger.Component;
import jendrzyca.piotr.fabloader.MainActivity;
import jendrzyca.piotr.fabloader.di.modules.MainActivityModule;
import jendrzyca.piotr.fabloader.di.modules.NetworkModule;
import jendrzyca.piotr.fabloader.di.scopes.PerActivity;
import jendrzyca.piotr.fabloader.ui.MainActivityPresenter;

/**
 * Created by huddy on 7/13/16.
 */
@PerActivity
@Component (modules = {NetworkModule.class , MainActivityModule.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
