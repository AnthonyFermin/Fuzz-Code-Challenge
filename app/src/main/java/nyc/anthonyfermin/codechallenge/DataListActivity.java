package nyc.anthonyfermin.codechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import nyc.anthonyfermin.codechallenge.adapters.DataListAdapter;
import nyc.anthonyfermin.codechallenge.objects.DisplayData;
import nyc.anthonyfermin.codechallenge.objects.DisplayDataList;
import nyc.anthonyfermin.codechallenge.request.DataListDeserializer;
import nyc.anthonyfermin.codechallenge.request.DataRetrofit;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by c4q-anthonyf on 10/3/15.
 */
public class DataListActivity extends AppCompatActivity {

    private RecyclerView dataListRV;
    private List<DisplayData> dataList;
    private static final String ENDPOINT = "http://quizzes.fuzzstaging.com/quizzes/mobile/1/data.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        bindViews();
        getData();
    }

    /*
     * Retrofit uses okhttp to retrieve the json from the endpoint,
     * then uses gson to convert the json into it's object representation.
     * The adapter is set once the data is retrieved and converted
     */
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
                if (dataList != null) {
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
        Log.d("DATALISTACTIVITY", "DATA SIZE:" + dataList.size());
        dataListRV.setLayoutManager(new LinearLayoutManager(this));
        DataListAdapter adapter = new DataListAdapter(this, dataList);
        dataListRV.setAdapter(adapter);
    }

    private void bindViews() {
        dataListRV = (RecyclerView) findViewById(R.id.data_list);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // no switch-case needed, about is the only option
        Intent aboutIntent = new Intent(this, AboutActivity.class);
        startActivity(aboutIntent);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
