package jendrzyca.piotr.fabloader.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import jendrzyca.piotr.fabloader.model.youtube.video_details.Statistics;
import jendrzyca.piotr.fabloader.model.youtube.video_details.StatisticsList;

/**
 * Created by Piotr Jendrzyca on 7/17/16.
 */
public class StatisticsDeserialzer implements JsonDeserializer<StatisticsList> {

    @Override
    public StatisticsList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        List<Statistics> stats = new ArrayList<>();
        JsonArray items = json.getAsJsonObject().get("items").getAsJsonArray();

        for (JsonElement je : items) {

            int viewCount = 0;
            int likeCount = 0;
            int dislikeCount = 0;
            int favoriteCount = 0;
            int commentCount = 0;
            String id = je.getAsJsonObject().get("id").getAsString();
            JsonElement contentDetails = je.getAsJsonObject().get("contentDetails");

            String duration = contentDetails.getAsJsonObject().get("duration").getAsString();

            try {
                JsonElement statistics = je.getAsJsonObject().get("statistics");
                viewCount = statistics.getAsJsonObject().get("viewCount").getAsInt();
                likeCount = statistics.getAsJsonObject().get("likeCount").getAsInt();
                dislikeCount = statistics.getAsJsonObject().get("dislikeCount").getAsInt();
                favoriteCount = statistics.getAsJsonObject().get("favoriteCount").getAsInt();
                commentCount = statistics.getAsJsonObject().get("commentCount").getAsInt();
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }

            Statistics videoStatiscics = new Statistics(id, duration, viewCount, likeCount, dislikeCount, favoriteCount, commentCount);

            stats.add(videoStatiscics);
        }


        return new StatisticsList(stats);
    }
}
