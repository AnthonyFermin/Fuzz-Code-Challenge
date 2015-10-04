package nyc.anthonyfermin.codechallenge.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DisplayData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("data")
    @Expose
    private String data;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getData() {
        return data;
    }


}