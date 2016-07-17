package jendrzyca.piotr.fabloader.model.youtube.video_details;

/**
 * Created by Piotr Jendrzyca on 7/17/16.
 *
 * Class to contain statistics for a video
 */
public class Statistics {

    private String id;
    private String duration;
    private int viewCount;
    private int likeCount;
    private int dislikeCount;

    public Statistics(String id, String duration, int viewCount, int likeCount, int dislikeCount, int favoriteCount, int commentCount) {
        this.id = id;
        this.duration = duration;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.favoriteCount = favoriteCount;
        this.commentCount = commentCount;
    }

    public Statistics() {

    }

    private int favoriteCount;
    private int commentCount;


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

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
