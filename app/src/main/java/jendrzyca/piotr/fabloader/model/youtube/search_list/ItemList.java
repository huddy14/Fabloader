package jendrzyca.piotr.fabloader.model.youtube.search_list;

import java.util.ArrayList;
import java.util.List;

import jendrzyca.piotr.fabloader.model.youtube.video_details.Statistics;

public class ItemList {
    private List<Item> items;

    public ItemList(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
