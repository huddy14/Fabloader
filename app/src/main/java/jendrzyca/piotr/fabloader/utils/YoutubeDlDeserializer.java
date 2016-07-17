package jendrzyca.piotr.fabloader.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import jendrzyca.piotr.fabloader.model.youtube_dl.Formats;

/**
 * Created by Piotr Jendrzyca on 7/16/16.
 */
public class YoutubeDlDeserializer implements JsonDeserializer<Formats> {


    @Override
    public Formats deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonElement info = json.getAsJsonObject().get("info");
        JsonArray formats = info.getAsJsonObject().get("formats").getAsJsonArray();

        Map<String, String> results = new HashMap<>();
        for (JsonElement je : formats) {
            String url = je.getAsJsonObject().get("url").getAsString();
            String ext = je.getAsJsonObject().get("ext").getAsString();

            results.put(ext, url);
        }
        Formats f = new Formats();
        f.setFormats(results);
        return f;
    }


}
