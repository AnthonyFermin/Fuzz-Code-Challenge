package nyc.anthonyfermin.codechallenge.request;

import nyc.anthonyfermin.codechallenge.objects.DisplayDataList;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Interface used for data retrieval with Retrofit
 */
public interface DataRetrofit {

    @GET("/")
    void getDisplayDataList(Callback<DisplayDataList> response);

}
