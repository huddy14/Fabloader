package jendrzyca.piotr.fabloader.ui.presenters;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

import jendrzyca.piotr.fabloader.model.youtube.search_list.YoutubeRespones;
import jendrzyca.piotr.fabloader.model.youtube.video_details.Statistics;
import jendrzyca.piotr.fabloader.model.youtube_dl.Formats;
import jendrzyca.piotr.fabloader.network.YoutubeApi;
import jendrzyca.piotr.fabloader.network.DownloadApi;
import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by huddy on 7/13/16.
 */
public class MainActivityPresenterImpl implements MainActivityPresenter.Presenter {

    private Retrofit retrofitYoutube;
    private Retrofit retrofitDownloader;
    private MainActivityPresenter.View view;
    private DownloadManager downloadManager;


    @Inject
    public MainActivityPresenterImpl(@Named("RetrofitYoutube") Retrofit retrofitYoutube,
                                     @Named("RetrofitDownloader") Retrofit retrofitDownloader,
                                     DownloadManager downloadManager,
                                     MainActivityPresenter.View view) {
        this.retrofitYoutube = retrofitYoutube;
        this.retrofitDownloader = retrofitDownloader;
        this.downloadManager = downloadManager;
        this.view = view;

    }

    @Override
    public void load(String query) {
//        retrofitYoutube.create(YoutubeApi.class).searchSongs(YoutubeApi.PART, YoutubeApi.MAX_RESULTS
//                , query, YoutubeApi.TYPE, YoutubeApi.KEY).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .unsubscribeOn(Schedulers.io())
//                .subscribe(new Observer<YoutubeRespones>() {
//                    @Override
//                    public void onCompleted() {
//                        view.onComplete();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        view.onError(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(YoutubeRespones list) {
//                        view.showResults(list.getItems());
//                        getVideoStatisctics(list.getItems().get(0).getId().getVideoId());
//                    }
//                });
        getVideoStatisctics("bUBdy3G33hA");
    }

    private void getVideoStatisctics(final String id)
    {
        retrofitYoutube.create(YoutubeApi.class).videoStatisctics(YoutubeApi.PART_STATISTICS, id, YoutubeApi.KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<Statistics>() {
                    @Override
                    public void onCompleted() {
                        Log.w("completed", ""+id);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w("blad", ""+id);

                    }

                    @Override
                    public void onNext(Statistics statistics) {
                        String duration = statistics.getDuration();
                    }
                });
    }

    @Override
    public void download(String id, final String tittle) {
        retrofitDownloader.create(DownloadApi.class).getSong(DownloadApi.LINK + id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<Formats>() {
                    @Override
                    public void onCompleted() {
                        view.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(Formats formats) {
                        downloadSong(tittle, formats.getFormats().get("m4a"));
                        view.onConverterResponse();
                    }
                });
    }

    private void downloadSong(String tittle, String url)
    {

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("FABUJĘ...");
        request.setTitle(tittle);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, tittle+".m4a");
        request.setVisibleInDownloadsUi(true);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        downloadManager.enqueue(request);
    }
}
