package jendrzyca.piotr.fabloader.ui.presenters;


import java.util.List;

import jendrzyca.piotr.fabloader.model.youtube.search_list.Item;

/**
 * Created by huddy on 7/13/16.
 */
public interface MainActivityPresenter {
    interface View {
        void showResults(List<Item> songs);

        void onConverterResponse();

        void onError(String errMessage);

        void onComplete();
    }

    interface Presenter {
        void load(String query);

        void download(String id, String tittle);
    }
}
