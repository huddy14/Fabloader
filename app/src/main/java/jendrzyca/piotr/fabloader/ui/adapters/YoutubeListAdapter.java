package jendrzyca.piotr.fabloader.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.Duration;

import butterknife.Bind;
import butterknife.ButterKnife;
import jendrzyca.piotr.fabloader.R;
import jendrzyca.piotr.fabloader.model.youtube.search_list.Item;
import jendrzyca.piotr.fabloader.ui.MainActivity;

/**
 * Created by huddy on 7/9/16.
 */
public class YoutubeListAdapter extends RecyclerView.Adapter<YoutubeListAdapter.YoutubeItemViewHolder> {

    private Context context;
    private List<Item> songs;
    private MainActivity mainActivity;

    public YoutubeListAdapter(List<Item> songs, MainActivity mainActivity) {
        this.mainActivity= mainActivity;
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
        holder.tittle.setText(songs.get(position).getTittle());
        Picasso.with(context)
                .load(songs.get(position).getThumbnailUrl())
                .fit()
                .into(holder.thumbnail);

        holder.like.setText(numberWithSpaces(songs.get(position).getStatistics().getLikeCount()));
        holder.dislike.setText(numberWithSpaces(songs.get(position).getStatistics().getDislikeCount()));
        holder.views.setText(numberWithSpaces(songs.get(position).getStatistics().getViewCount()));

        holder.description.setText(songs.get(position).getDescription());
        String date = songs.get(position).getPublishedAt();
        holder.pubDate.setText(parseDate(date));
        holder.duration.setText(parseTime(songs.get(position).getStatistics().getDuration()));

        // handling image area click
        //holder.imageArea.setOnClickListener(mainActivity);
    }


    private String numberWithSpaces(String x) {
        Long number = Long.parseLong(x);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');

        DecimalFormat df = new DecimalFormat();
        df.setDecimalFormatSymbols(symbols);
        df.setGroupingSize(3);
        df.setMaximumFractionDigits(2);
        return df.format(number);
        //return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ");
        //return String.valueOf(x).replace("/\\B(?=(\\d{3})+(?!\\d))/g", " ");
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
        @Bind(R.id.duration)TextView duration;
        @Bind(R.id.likeTV)TextView like;
        @Bind(R.id.dislikeTV)TextView dislike;
        @Bind(R.id.viewsTV)TextView views;

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
        return songs.get(positoin).getId();
    }

    public String getSongTittle(int position) {
        return songs.get(position).getTittle();
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

        return new SimpleDateFormat("dd/MM/yyyy").format(d);
    }

    //// TODO: 7/17/16 spacje wrzucic dla czytelnosci7 - zrobione: Michal
    private String parseTime(String duration) {
        return duration.substring(2).replace("M", "M ");
    }
}
