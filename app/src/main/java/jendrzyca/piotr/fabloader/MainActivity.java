package jendrzyca.piotr.fabloader;

import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import jendrzyca.piotr.fabloader.View.RecyclerViewListener;
import jendrzyca.piotr.fabloader.View.YoutubeListAdapter;
import jendrzyca.piotr.fabloader.YouTube.Connector;
import jendrzyca.piotr.fabloader.YouTube.Downloader;
import jendrzyca.piotr.fabloader.YouTube.Item;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, RecyclerViewListener.SongItemEventListener {

    Connector connector;
    ArrayList<Item> items;

    @Bind(R.id.rv)
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Stetho.initializeWithDefaults(this);
        Item test = new Item();
        test.setThumbnailURL("asd");
        test.setTittle("test");
        test.setDescription("opis");
        test.setThumbnailURL("asdsD");
        ArrayList<Item> items = new ArrayList<>();
        //rv.setAdapter(new YoutubeListAdapter(items));
        rv.addOnItemTouchListener(new RecyclerViewListener(getApplicationContext(), this));
        connector = new Connector(this);
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


    private void updateAdapter() {
        rv.setAdapter(new YoutubeListAdapter(items));
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        FetchSongsData fds = new FetchSongsData();
        fds.execute(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onTouch(int position) {
        Downloader.getInstance(this).download(items.get(position));
    }

    private class FetchSongsData extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            items = connector.search(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            updateAdapter();
        }
    }
}
