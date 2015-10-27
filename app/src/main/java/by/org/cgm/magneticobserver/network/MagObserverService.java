package by.org.cgm.magneticobserver.network;

import by.org.cgm.magneticobserver.models.response.GetDataResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Author: Anatol Salanevich
 * Date: 01.10.2015
 */
public interface MagObserverService {

    @GET(ApiConstants.GET_DATA)
    void getDataRequest(@Query("date") String date, Callback<GetDataResponse> callback);

    @GET(ApiConstants.GET_MIDDLE)
    void getMiddleRequest(Callback<GetDataResponse> callback);

}
