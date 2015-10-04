package nyc.anthonyfermin.codechallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by c4q-anthonyf on 10/3/15.
 */
public class DataListActivity extends AppCompatActivity {

    RecyclerView dataListRV;
    List<DisplayData> dataList;
    private static final String ENDPOINT = "http://quizzes.fuzzstaging.com/quizzes/mobile/1/data.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        bindViews();
        getData();
    }

    private void getData() {
        dataList = new ArrayList<>();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setConverter(new GsonConverter(new GsonBuilder()
                        .registerTypeAdapter(DisplayDataList.class, new DataListDeserializer())
                        .create()))
                .build();

        DataRetrofit dataRetrofit = restAdapter.create(DataRetrofit.class);

        dataRetrofit.getDisplayDataList(new Callback<DisplayDataList>() {
            @Override
            public void success(DisplayDataList displayDataList, Response response) {
                dataList = displayDataList.getDisplayDataList();
                if(dataList != null) {
                    setAdapter();
                    Log.d("DataListActivity", "retrofit success");
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("DataListActivity", "retrofit fail");
                error.printStackTrace();
            }
        });
    }

    private void setAdapter() {
        dataListRV.setLayoutManager(new LinearLayoutManager(this));
        dataListRV.setHasFixedSize(true);
        DataListAdapter adapter = new DataListAdapter(this, dataList);
        dataListRV.setAdapter(adapter);
    }

    private void bindViews() {
        dataListRV = (RecyclerView) findViewById(R.id.data_list);
    }
}
