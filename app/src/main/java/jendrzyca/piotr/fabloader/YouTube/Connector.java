package jendrzyca.piotr.fabloader.YouTube;

import android.content.Context;
import android.util.Log;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jendrzyca.piotr.fabloader.R;

/**
 * Created by huddy on 7/9/16.
 */

public class Connector {
    private final String API_KEY = "AIzaSyApKahzf_cB_dx8JYyXHocto5-YXY8ais4";

    private final String LIST_ELEMENTS = "id,snippet";
    private final String TYPE = "video";
    private final String FIELDS = "items(id/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url)";

    private YouTube youtube;
    private YouTube.Search.List query;

    private Context context;

    public Connector(Context context)
    {
        this.context = context;

        youtube = new YouTube.Builder(new NetHttpTransport(),
                new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest hr) throws IOException {}
        }).setApplicationName(context.getString(R.string.app_name)).build();

        try{
            query = youtube.search().list(LIST_ELEMENTS);
            query.setKey(API_KEY);
            query.setType(TYPE);
            query.setFields(FIELDS);
        }
        catch(IOException e){
            Log.d("YC", "Could not initialize: " + e);
        }
    }

    public ArrayList<Item> search(String keywords)
    {
        query.setQ(keywords);
        query.setMaxResults(30L);

        ArrayList<Item> items = new ArrayList<>();

        try {
            SearchListResponse response = query.execute();

            List<SearchResult> results = response.getItems();


            for(SearchResult result : results)
            {
                Item item = new Item();

                item.setId(result.getId().getVideoId());
                item.setDescription(result.getSnippet().getDescription());
                item.setTittle(result.getSnippet().getTitle());
                item.setThumbnailURL(result.getSnippet().getThumbnails().getDefault().getUrl());

                items.add(item);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;

    }
}
