package jendrzyca.piotr.fabloader.ui.presenters;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import jendrzyca.piotr.fabloader.model.youtube.search_list.Item;
import jendrzyca.piotr.fabloader.model.youtube.search_list.YoutubeRespones;
import jendrzyca.piotr.fabloader.model.youtube.video_details.Statistics;
import jendrzyca.piotr.fabloader.model.youtube.video_details.StatisticsList;
import jendrzyca.piotr.fabloader.model.youtube_dl.Formats;
import jendrzyca.piotr.fabloader.network.DownloadApi;
import jendrzyca.piotr.fabloader.network.YoutubeApi;
import jendrzyca.piotr.fabloader.utils.StringModifires;
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
    private Retrofit retrofitVideoDetails;
    private MainActivityPresenter.View view;
    private DownloadManager downloadManager;

    private List<Item> youtubeItems = new ArrayList<>();


    @Inject
    public MainActivityPresenterImpl(@Named("RetrofitYoutube") Retrofit retrofitYoutube,
                                     @Named("RetrofitDownloader") Retrofit retrofitDownloader,
                                     @Named("RetrofitVideoDetails") Retrofit retrofitVideoDetails,
                                     DownloadManager downloadManager,
                                     MainActivityPresenter.View view) {
        this.retrofitYoutube = retrofitYoutube;
        this.retrofitDownloader = retrofitDownloader;
        this.retrofitVideoDetails = retrofitVideoDetails;
        this.downloadManager = downloadManager;
        this.view = view;

    }

    @Override
    public void load(String query) {
        retrofitYoutube.create(YoutubeApi.class).searchSongs(YoutubeApi.PART, YoutubeApi.MAX_RESULTS
                , query, YoutubeApi.TYPE, YoutubeApi.KEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<YoutubeRespones>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(YoutubeRespones list) {

                        youtubeItems = list.getItems();

                        getVideoStatisctics(StringModifires.idsBuilder(youtubeItems));
                    }
                });
    }

    private void getVideoStatisctics(final String ids) {
        retrofitVideoDetails.create(YoutubeApi.class).videoStatisctics(YoutubeApi.PART_STATISTICS, ids, YoutubeApi.KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<StatisticsList>() {
                    @Override
                    public void onCompleted() {
                        view.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(StatisticsList list) {
                        appendStatistics(list.getItems());
                        view.showResults(youtubeItems);

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

    private void appendStatistics(List<Statistics> statistics) {
        for (int i = 0 ; i < youtubeItems.size(); i++) {
            youtubeItems.get(i).setStatistics(statistics.get(i));
        }
    }
    private void downloadSong(String tittle, String url) {

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("FABUJĘ...");
        request.setTitle(tittle);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, tittle + ".m4a");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        downloadManager.enqueue(request);
    }
}
