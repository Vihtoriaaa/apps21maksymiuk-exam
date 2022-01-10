package json;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    private ArrayList<JsonPair> list = new ArrayList<>();

    public JsonObject(JsonPair... jsonPairs) {
        list.addAll(Arrays.asList(jsonPairs));
    }

    @Override
    public String toJson() {
        StringBuilder json = new StringBuilder("{");
        for (JsonPair aList : list) {
            json.append("'").append(aList.key).append("'").append(": ").append(aList.value.toJson()).append(",");
        }
        if(json.length() != 1) {
            json.deleteCharAt(json.length() - 1);
        }
        json.append("}");
        return json.toString();
    }

    public void add(JsonPair jsonPair) {
        list.add(jsonPair);
    }

    public Json find(String name) {
        for (JsonPair oneList : list) {
            if (oneList.key.equals(name)) {
                return oneList.value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject json = new JsonObject();
        ArrayList <String> name = new ArrayList<>();
        name.addAll(Arrays.asList(names));
        for (JsonPair oneList: list) {
            if (name.contains(oneList.key)) {
                json.add(oneList);
            }
        }
        return json;
    }
}
