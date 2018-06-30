package br.com.unclephill.meuddd.API;

import br.com.unclephill.meuddd.Object.LoginObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAPI {
    @POST("Login/Login")
    Call<LoginObject> setLogin(@Body LoginObject loginObject);
}
