package jendrzyca.piotr.fabloader.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jendrzyca.piotr.fabloader.R;
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
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }


    protected class YoutubeItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.descriptionTV) TextView description;
        @Bind(R.id.tittleTV)TextView tittle;
        @Bind(R.id.thumbnail)ImageView thumbnail;

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
}
