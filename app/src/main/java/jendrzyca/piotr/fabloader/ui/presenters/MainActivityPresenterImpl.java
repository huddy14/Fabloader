package jendrzyca.piotr.fabloader.ui.presenters;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;

import javax.inject.Inject;
import javax.inject.Named;

import jendrzyca.piotr.fabloader.model.converter.SongDownload;
import jendrzyca.piotr.fabloader.model.youtube.YoutubeRespones;
import jendrzyca.piotr.fabloader.network.ConverterApi;
import jendrzyca.piotr.fabloader.network.YoutubeApi;
import jendrzyca.piotr.fabloader.utils.Constants;
import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by huddy on 7/13/16.
 */
public class MainActivityPresenterImpl implements MainActivityPresenter.Presenter {

    private Retrofit retrofitYoutube;
    private Retrofit retrofitConverter;
    private MainActivityPresenter.View view;
    private DownloadManager downloadManager;


    @Inject
    public MainActivityPresenterImpl(@Named("RetrofitYoutube") Retrofit retrofitYoutube,
                                     @Named("RetrofitConventer") Retrofit retrofitConverter,
                                     DownloadManager downloadManager,
                                     MainActivityPresenter.View view) {
        this.retrofitYoutube = retrofitYoutube;
        this.retrofitConverter = retrofitConverter;
        this.downloadManager = downloadManager;
        this.view = view;
    }

    @Override
    public void load(String query) {
        retrofitYoutube.create(YoutubeApi.class).searchSongs(Constants.YOUTUBE_PART, Constants.YOUTUBE_MAX_RESULTS
                , query, Constants.YOUTUBE_TYPE, Constants.YOUTUBE_KEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<YoutubeRespones>() {
                    @Override
                    public void onCompleted() {
                        view.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(YoutubeRespones list) {
                        view.showResults(list.getItems());
                    }
                });
    }

    @Override
    public void download(String id) {
        retrofitConverter.create(ConverterApi.class).getSong(Constants.CONVERTER_JSON,Constants.CONVERTER_YOUTUBE_LINK + id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<SongDownload>() {
                    @Override
                    public void onCompleted() {
                        view.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(SongDownload songDownload) {
                        downloadSong(songDownload);
                        view.onConverterResponse(songDownload);
                    }
                });
    }

    private void downloadSong(SongDownload songDownload)
    {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(songDownload.getLink()));
        request.setDescription("FABUJĘ...");
        request.setTitle(songDownload.getTitle());
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, songDownload.getTitle()+".mp3");
        request.setVisibleInDownloadsUi(true);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        downloadManager.enqueue(request);
    }
}
