package nyc.anthonyfermin.codechallenge;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c4q-anthonyf on 10/4/15.
 */
public class DisplayDataList {

    @Expose
    List<DisplayData> displayDataList;

    public List<DisplayData> getDisplayDataList() {
        return displayDataList;
    }

    public void add(DisplayData displayData){
        if(displayDataList == null){
            displayDataList = new ArrayList<>();
        }

        displayDataList.add(displayData);
    }
}
