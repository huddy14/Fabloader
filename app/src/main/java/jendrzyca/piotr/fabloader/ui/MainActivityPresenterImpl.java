package jendrzyca.piotr.fabloader.ui;

import com.google.api.services.youtube.YouTube;

import javax.inject.Inject;
import javax.inject.Named;

import jendrzyca.piotr.fabloader.MainActivity;
import jendrzyca.piotr.fabloader.network.YoutubeApi;
import jendrzyca.piotr.fabloader.utils.Constants;
import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by huddy on 7/13/16.
 */
public class MainActivityPresenterImpl implements MainActivityPresenter.Presenter{

    private Retrofit retrofit;
    private MainActivityPresenter.View view;

    @Inject
    @Named("RetrofitYoutube")
    public MainActivityPresenterImpl(Retrofit retrofit, MainActivityPresenter.View view)
    {
        this.retrofit =retrofit;
        this.view = view;
    }

    @Override
    public void load(String query) {
        retrofit.create(YoutubeApi.class).searchSongs(Constants.YOUTUBE_PART,Constants.YOUTUBE_MAX_RESULTS
        ,query,Constants.YOUTUBE_KEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<YouTube.Search.List>() {
                    @Override
                    public void onCompleted() {
                        view.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(YouTube.Search.List list) {
                        view.showResults(list);
                    }
                });
    }
}
