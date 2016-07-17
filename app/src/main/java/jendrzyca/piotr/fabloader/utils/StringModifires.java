package jendrzyca.piotr.fabloader.utils;

import java.util.List;

import jendrzyca.piotr.fabloader.model.youtube.search_list.Item;

/**
 * Created by Piotr Jendrzyca on 7/17/16.
 */
public final class StringModifires {

    public static String idsBuilder(List<Item>items)
    {
        StringBuilder builder = new StringBuilder();

        for (Item i : items) {
            builder.append(i.getId()).append(",");
        }

        String result = builder.toString();
        return result.substring(0, result.length() - 1);
    }
}
