package by.org.cgm.magneticobserver.network;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Author: Anatol Salanevich
 * Date: 01.10.2015
 */
public class API {

    private static API sInstance;

    private MagObserverService mService;

    private API() {
        OkHttpClient client = new OkHttpClient();
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ApiConstants.BASE_URL)
                .setConverter(new JacksonConverter())
                .setClient(new OkClient(client))
                .build();
        mService = adapter.create(MagObserverService.class);
    }

    public static void initialize() {
        sInstance = new API();
    }

    public static API getInstance() {
        return sInstance;
    }

    public MagObserverService getService() {
        return mService;
    }

}
