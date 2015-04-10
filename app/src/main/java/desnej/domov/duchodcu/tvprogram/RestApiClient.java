package desnej.domov.duchodcu.tvprogram;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by program2 on 10.4.2015.
 */
public class RestApiClient
{
    private static RestApi REST_CLIENT;

    static
    {
        setupRestClient();
    }

    private RestApiClient()
    {
    }

    public static RestApi get()
    {
        return REST_CLIENT;
    }

    private static void setupRestClient()
    {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint("http://android.tomasrosa.com/tv/rest_api.php")
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(RestApi.class);
    }
}

