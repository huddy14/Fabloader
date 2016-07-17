package jendrzyca.piotr.fabloader.model.youtube.video_details;

import java.util.List;

/**
 * Created by Piotr Jendrzyca on 7/17/16.
 */
public class StatisticsList {
    private List<Statistics> items;

    public StatisticsList(List<Statistics> items) {
        this.items = items;
    }

    public List<Statistics> getItems() {
        return items;
    }

    public void setItems(List<Statistics> items) {
        this.items = items;
    }
}
