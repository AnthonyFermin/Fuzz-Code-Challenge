package nyc.anthonyfermin.codechallenge.request;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import nyc.anthonyfermin.codechallenge.objects.DisplayData;
import nyc.anthonyfermin.codechallenge.objects.DisplayDataList;

/**
 * Converts JsonArray into an ArrayList of DisplayData
 */

public class DataListDeserializer implements JsonDeserializer<DisplayDataList> {
    @Override
    public DisplayDataList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        DisplayDataList displayDataList = new DisplayDataList();
        JsonArray jsonArray = json.getAsJsonArray();
        Log.d("Deserializer","ARRAY SIZE: " + jsonArray.size());
        Gson gson = new Gson();
        for(JsonElement element : jsonArray){
            JsonObject jsonObject = element.getAsJsonObject();
            DisplayData data = gson.fromJson(jsonObject, DisplayData.class);
            displayDataList.add(data);
        }
        return displayDataList;

    }
}
