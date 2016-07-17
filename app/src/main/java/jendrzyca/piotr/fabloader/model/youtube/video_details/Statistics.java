package jendrzyca.piotr.fabloader.model.youtube.video_details;

/**
 * Created by Piotr Jendrzyca on 7/17/16.
 */
public class Statistics {

    private String id;
    private String duration;
    private String viewCount;
    private String likeCount;
    private String dislikeCount;
    private String favoriteCount;
    private String commentCount;

    public Statistics() {
    }

    public Statistics(String id, String duration, String viewCount, String likeCount, String dislikeCount, String favoriteCount, String commentCount) {
        this.id = id;
        this.duration = duration;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.favoriteCount = favoriteCount;
        this.commentCount = commentCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(String dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public String getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(String favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }
}
