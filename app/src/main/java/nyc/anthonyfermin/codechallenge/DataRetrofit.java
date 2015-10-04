package nyc.anthonyfermin.codechallenge;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by c4q-anthonyf on 10/3/15.
 */
public interface DataRetrofit {

    @GET("/")
    void getDisplayDataList(Callback<DisplayDataList> response);

}
