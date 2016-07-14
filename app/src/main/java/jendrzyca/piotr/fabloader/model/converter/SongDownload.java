package jendrzyca.piotr.fabloader.model.converter;

/**
 * Created by huddy on 7/13/16.
 */
public class SongDownload {
    private long length;
    private String tittle;
    private String link;

    public SongDownload(String tittle, long length, String link) {
        this.length = length;
        this.link = link;
        this.tittle = tittle;
    }

    public long getLength() {
        return length;
    }

    public String getTittle() {
        return tittle;
    }

    public String getLink() {
        return link;
    }
}
