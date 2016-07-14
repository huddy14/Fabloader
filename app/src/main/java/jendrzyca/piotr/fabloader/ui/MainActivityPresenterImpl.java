package jendrzyca.piotr.fabloader.ui;

import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import jendrzyca.piotr.fabloader.di.scopes.PerActivity;
import jendrzyca.piotr.fabloader.model.youtube.YoutubeRespones;
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
    //private Retrofit retrofitConverter;
    private MainActivityPresenter.View view;
//
//    @Named("RetrofitYoutube")Retrofit retrofitYoutube,
//    @Named("RetrofitConverter")Retrofit retrofitConverter,
    @Inject
    public MainActivityPresenterImpl(Retrofit retrofit,
                                     MainActivityPresenter.View view)
    {
        this.retrofit =retrofit;
        //this.retrofitConverter = retrofitConverter;
        this.view = view;
    }

    @Override
    public void load(String query) {
        retrofit.create(YoutubeApi.class).searchSongs(Constants.YOUTUBE_PART,Constants.YOUTUBE_MAX_RESULTS
        ,query,Constants.YOUTUBE_TYPE,Constants.YOUTUBE_KEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<YoutubeRespones>() {
                    @Override
                    public void onCompleted() {
                        view.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(YoutubeRespones list) {
                        view.showResults(list.getItems());
                    }
                });
    }

    @Override
    public void download(String id) {

    }
}
