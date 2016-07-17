package jendrzyca.piotr.fabloader.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import jendrzyca.piotr.fabloader.model.youtube.video_details.Statistics;

/**
 * Created by Piotr Jendrzyca on 7/17/16.
 */
public class StatisticsDeserialzer implements JsonDeserializer<Statistics> {

    @Override
    public Statistics deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonArray items = json.getAsJsonObject().get("items").getAsJsonArray();

        for (JsonElement je : items) {

            String id = je.getAsJsonObject().get("id").getAsString();
            JsonElement contentDetails = je.getAsJsonObject().get("contentDetails");

            String duration = contentDetails.getAsJsonObject().get("duration").getAsString();

            JsonElement statistics = je.getAsJsonObject().get("statistics");
            int viewCount = statistics.getAsJsonObject().get("viewCount").getAsInt();
            int likeCount = statistics.getAsJsonObject().get("likeCount").getAsInt();
            int dislikeCount = statistics.getAsJsonObject().get("dislikeCount").getAsInt();
            int favoriteCount = statistics.getAsJsonObject().get("favoriteCount").getAsInt();
            int commentCount = statistics.getAsJsonObject().get("commentCount").getAsInt();

            Statistics videoStatiscics = new Statistics(id, duration, viewCount, likeCount, dislikeCount, favoriteCount, commentCount);

            return videoStatiscics;
        }


        return null ;
    }
}
