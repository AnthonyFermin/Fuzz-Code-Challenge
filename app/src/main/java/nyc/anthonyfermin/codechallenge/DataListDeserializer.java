package nyc.anthonyfermin.codechallenge;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by c4q-anthonyf on 10/4/15.
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
