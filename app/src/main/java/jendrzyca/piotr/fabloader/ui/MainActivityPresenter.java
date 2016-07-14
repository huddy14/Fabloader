package jendrzyca.piotr.fabloader.ui;


import java.util.List;

import jendrzyca.piotr.fabloader.model.youtube.Item;

/**
 * Created by huddy on 7/13/16.
 */
public interface MainActivityPresenter {
    interface View
    {
        void showResults(List<Item> songs);

        void showError(String errMessage);

        void showComplete();
    }

    interface Presenter
    {
        void load(String query);

        void download(String id);
    }
}
