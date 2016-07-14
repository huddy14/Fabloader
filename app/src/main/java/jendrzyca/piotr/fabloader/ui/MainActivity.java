package jendrzyca.piotr.fabloader.ui;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import jendrzyca.piotr.fabloader.Fabloader;
import jendrzyca.piotr.fabloader.R;
import jendrzyca.piotr.fabloader.View.RecyclerViewListener;
import jendrzyca.piotr.fabloader.View.YoutubeListAdapter;
import jendrzyca.piotr.fabloader.di.components.DaggerMainActivityComponent;
import jendrzyca.piotr.fabloader.di.modules.MainActivityModule;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,
        RecyclerViewListener.SongItemEventListener, MainActivityPresenter.View  {

    @Inject
    MainActivityPresenterImpl presenter;

    @Bind(R.id.rv)RecyclerView rc;

    YoutubeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        rc.setLayoutManager(new LinearLayoutManager(this));

        adapter = new YoutubeListAdapter(new ArrayList<jendrzyca.piotr.fabloader.model.youtube.Item>());

        rc.setAdapter(adapter);

        DaggerMainActivityComponent.builder()
                .networkComponent(((Fabloader)getApplication()).getNetworkComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build().inject(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        return true;
    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        presenter.load(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onTouch(int position) {
        //Downloader.getInstance(this).download(items.get(position));
    }


    @Override
    public void showResults(List<jendrzyca.piotr.fabloader.model.youtube.Item> songs) {
        adapter.update(songs);

    }

    @Override
    public void showError(String errMessage) {
        Log.w("BLAD",errMessage);
    }

    @Override
    public void showComplete() {

    }

}
