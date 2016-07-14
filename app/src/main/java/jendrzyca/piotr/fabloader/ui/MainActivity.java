package jendrzyca.piotr.fabloader.ui;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

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
import jendrzyca.piotr.fabloader.model.converter.SongDownload;
import jendrzyca.piotr.fabloader.model.youtube.Item;
import jendrzyca.piotr.fabloader.ui.presenters.MainActivityPresenter;
import jendrzyca.piotr.fabloader.ui.presenters.MainActivityPresenterImpl;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,
        RecyclerViewListener.SongItemEventListener, MainActivityPresenter.View  {

    @Inject
    MainActivityPresenterImpl presenter;

    @Bind(R.id.rv)RecyclerView rc;
    @Bind(R.id.loader)CircularFillableLoaders progress;

    YoutubeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initializeRecyclerView();
        initializeProgress();

        DaggerMainActivityComponent.builder()
                .networkComponent(((Fabloader)getApplication()).getNetworkComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build().inject(this);
    }

    private void initializeRecyclerView()
    {
        rc.setLayoutManager(new LinearLayoutManager(this));

        adapter = new YoutubeListAdapter(new ArrayList<jendrzyca.piotr.fabloader.model.youtube.Item>());

        rc.setAdapter(adapter);

        rc.addOnItemTouchListener(new RecyclerViewListener(this,this));
    }

    private void initializeProgress() {
        progress.setProgress(90);
        //progress.setColor(R.color.colorPrimaryDark);
    }

    private void displayProgress() {
        progress.setVisibility(View.VISIBLE);
        rc.setVisibility(View.INVISIBLE);
    }

    private void displaySonglist() {
        rc.setVisibility(View.VISIBLE);
        progress.setVisibility(View.INVISIBLE);
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
        //właczenie progress baru
        presenter.load(query);

        displayProgress();

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    //Przyciśnięcie itemu
    @Override
    public void onTouch(String id) {
        presenter.download(id);

        displayProgress();
    }


    @Override
    public void showResults(List<Item> songs) {
        adapter.update(songs);

        displaySonglist();
    }

    @Override
    public void onConverterResponse(SongDownload songDownload) {
        showMessage(getString(R.string.communicate_fabuje)+songDownload.getTitle());
    }

    @Override
    public void onError(String errMessage) {
        displaySonglist();
        showMessage(errMessage);
    }

    @Override
    public void onComplete() {
        //wyłaczenie progreesbaru
        displaySonglist();
    }

    private void showMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
