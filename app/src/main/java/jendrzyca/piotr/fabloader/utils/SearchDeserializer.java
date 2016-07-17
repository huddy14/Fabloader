package jendrzyca.piotr.fabloader.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import jendrzyca.piotr.fabloader.model.youtube.search_list.Item;
import jendrzyca.piotr.fabloader.model.youtube.search_list.YoutubeRespones;

/**
 * Created by Piotr Jendrzyca on 7/17/16.
 */
public class SearchDeserializer implements JsonDeserializer<YoutubeRespones> {

    @Override
    public YoutubeRespones deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Item> itemzz = new ArrayList<>();

        JsonArray items = json.getAsJsonObject().get("items").getAsJsonArray();

        for(JsonElement je : items)
        {
            JsonElement id = je.getAsJsonObject().get("id");

            String itemId = id.getAsJsonObject().get("videoId").getAsString();

            JsonElement snippet = je.getAsJsonObject().get("snippet");
            String itemTittle = snippet.getAsJsonObject().get("title").getAsString();
            String itemDescription = snippet.getAsJsonObject().get("description").getAsString();
            String itemPublishedAt = snippet.getAsJsonObject().get("publishedAt").getAsString();

            JsonElement thumbnails = snippet.getAsJsonObject().get("thumbnails");

            JsonElement thumbnailHigh = thumbnails.getAsJsonObject().get("high");
            String itemThumbnailHigh = thumbnailHigh.getAsJsonObject().get("url").getAsString();

            Item newItem = new Item(itemId, itemPublishedAt,itemTittle,itemDescription,itemThumbnailHigh);

            itemzz.add(newItem);
        }

        return new YoutubeRespones(itemzz);
    }
}
