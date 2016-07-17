package jendrzyca.piotr.fabloader.model.youtube.search_list;

import jendrzyca.piotr.fabloader.model.youtube.video_details.Statistics;

public class Item {

    private String id;
    private String publishedAt;
    private String tittle;
    private String description;
    private String thumbnailUrl;
    private Statistics statistics;

    public Item(String id, String publishedAt, String tittle, String description, String thumbnailUrl) {
        this.id = id;
        this.publishedAt = publishedAt;
        this.tittle = tittle;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Item() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }
}
