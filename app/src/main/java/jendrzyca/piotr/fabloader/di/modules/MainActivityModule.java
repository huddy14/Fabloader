package jendrzyca.piotr.fabloader.di.modules;

import dagger.Module;
import dagger.Provides;
import jendrzyca.piotr.fabloader.di.scopes.PerActivity;
import jendrzyca.piotr.fabloader.ui.presenters.MainActivityPresenter;

/**
 * Created by huddy on 7/13/16.
 */
@Module
public class MainActivityModule {
    private final MainActivityPresenter.View view;

    public MainActivityModule(MainActivityPresenter.View view)
    {
        this.view = view;
    }

    @Provides
    @PerActivity
    MainActivityPresenter.View providesMainActivityPresenterView()
    {
        return this.view;
    }
}
