package jendrzyca.piotr.fabloader.model.converter;

/**
 * Created by huddy on 7/13/16.
 */
public class SongDownload {
    private long length;
    private String title;
    private String link;

    public SongDownload(String title, long length, String link) {
        this.length = length;
        this.link = link;
        this.title = title;
    }

    public long getLength() {
        return length;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }
}
