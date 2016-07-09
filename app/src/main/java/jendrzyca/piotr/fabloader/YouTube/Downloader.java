package jendrzyca.piotr.fabloader.YouTube;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

/**
 * Created by huddy on 7/9/16.
 */
public class Downloader {

    private static Downloader instance;
    private Context context;

    private DownloadManager downloadManager;
    private DownloadManager.Request request;

    private final String youtubeURLheader = "http://www.youtubeinmp3.com/fetch/?video=http://www.youtube.com/watch?v=";


    public static Downloader getInstance(Context context)
    {
        if(instance==null)
            instance = new Downloader(context);
        return instance;
    }

    private Downloader(Context context)
    {
        this.context = context;
        downloadManager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
    }

    public void download(Item item)
    {
        String url = youtubeURLheader + item.getId();

        request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(item.getTittle());
        request.setDescription("Fabuuuje");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, item.getTittle()+".mp3");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);

        downloadManager.enqueue(request);

    }


}
