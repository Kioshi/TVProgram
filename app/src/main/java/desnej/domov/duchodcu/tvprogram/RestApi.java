package desnej.domov.duchodcu.tvprogram;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by program2 on 10.4.2015.
 */
public interface RestApi
{

    @GET("/get/{station}")
    void getGuide(@Path("station") String station, Callback<ArrayList<GuideItem>> callback);
}
