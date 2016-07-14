package jendrzyca.piotr.fabloader.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jendrzyca.piotr.fabloader.R;
import jendrzyca.piotr.fabloader.model.converter.SongDownload;
import jendrzyca.piotr.fabloader.model.youtube.Item;

/**
 * Created by huddy on 7/9/16.
 */
public class YoutubeListAdapter extends RecyclerView.Adapter<YoutubeListAdapter.YoutubeItemViewHolder> {

    private Context context;
    private List<Item> songs;

    public YoutubeListAdapter(List<Item> songs) {
        this.songs = songs;
    }

    @Override
    public YoutubeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        YoutubeItemViewHolder vh = new YoutubeItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(YoutubeItemViewHolder holder, int position) {
        holder.description.setText(songs.get(position).getSnippet().getDescription());
        holder.tittle.setText(songs.get(position).getSnippet().getTitle());
        Picasso.with(context)
                .load(songs.get(position).getSnippet().getThumbnails().getDefault().getUrl())
                .into(holder.thumbnail);
        String date = songs.get(position).getSnippet().getPublishedAt();
        holder.pubDate.setText(parseDate(date));
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }


    protected class YoutubeItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.descriptionTV) TextView description;
        @Bind(R.id.tittleTV)TextView tittle;
        @Bind(R.id.thumbnail)ImageView thumbnail;
        @Bind(R.id.pubDate)TextView pubDate;

        public YoutubeItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void update(List<Item> newSongList)
    {
        songs = newSongList;
        notifyDataSetChanged();
    }

    public String getSongId(int positoin) {
        return songs.get(positoin).getId().getVideoId();
    }

    private String parseDate(String date)
    {
        Date d;
        try {
            d= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(date);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }

        return new SimpleDateFormat("k:m, dd/MM/yyyy").format(d);
    }
}
