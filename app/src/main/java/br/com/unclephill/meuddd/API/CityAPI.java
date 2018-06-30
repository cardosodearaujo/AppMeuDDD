package br.com.unclephill.meuddd.API;

import java.util.List;

import br.com.unclephill.meuddd.Object.DDDCityObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CityAPI {
    @POST("webapi/getcidades")
    Call<List<DDDCityObject>> getCidades(@Body DDDCityObject dddCityObject);
}
