package json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private LinkedHashMap<String, Json> jsonPairs;

    public JsonObject(JsonPair... jsonPairs) {
        this.jsonPairs = new LinkedHashMap<>();
        for (JsonPair pair:jsonPairs){
            this.jsonPairs.put(pair.key, pair.value);
        }
    }

    @Override
    public String toJson() {
        if (this.jsonPairs.keySet().isEmpty()){
            return "{}";
        }

        StringBuilder result = new StringBuilder();
        result.append("{");
        for (String k:this.jsonPairs.keySet()){
            result.append(String.format("'%s' : %s, ", k, this.jsonPairs.get(k).toJson()));
        }
        result.replace(result.length() - 2, result.length(), "");
        result.append("}");
        return result.toString();
    }

    public void add(JsonPair jsonPair) {
        this.jsonPairs.put(jsonPair.key, jsonPair.value);
    }

    public boolean contains(String name){
        return this.jsonPairs.get(name) != null;
    }

    public Json find(String name) {
        return this.jsonPairs.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject filteredObject = new JsonObject();
        for (String name:names){
            if (contains(name)){
                filteredObject.add(new JsonPair(name, this.jsonPairs.get(name)));
            }
        }
        return filteredObject;
    }
}
