package jendrzyca.piotr.fabloader.ui;

import com.google.api.services.youtube.YouTube;

import java.util.ArrayList;

/**
 * Created by huddy on 7/13/16.
 */
public interface MainActivityPresenter {
    interface View
    {
        void showResults(YouTube.Search.List songs);

        void showError(String errMessage);

        void showComplete();
    }

    interface Presenter
    {
        void load(String query);
    }
}
