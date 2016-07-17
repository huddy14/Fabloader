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

            String viewCount;
            String likeCount;
            String dislikeCount;
            String favoriteCount;
            String commentCount;
            String id = je.getAsJsonObject().get("id").getAsString();
            JsonElement contentDetails = je.getAsJsonObject().get("contentDetails");

            String duration = contentDetails.getAsJsonObject().get("duration").getAsString();

            try {
                JsonElement statistics = je.getAsJsonObject().get("statistics");
                viewCount = statistics.getAsJsonObject().get("viewCount").getAsString();
                likeCount = statistics.getAsJsonObject().get("likeCount").getAsString();
                dislikeCount = statistics.getAsJsonObject().get("dislikeCount").getAsString();
                favoriteCount = statistics.getAsJsonObject().get("favoriteCount").getAsString();
                commentCount = statistics.getAsJsonObject().get("commentCount").getAsString();
            }
            catch (Exception e)
            {
                throw new RuntimeException();
            }

            Statistics videoStatiscics = new Statistics(id, duration, viewCount, likeCount, dislikeCount, favoriteCount, commentCount);

            stats.add(videoStatiscics);
        }


        return new StatisticsList(stats);
    }
}
