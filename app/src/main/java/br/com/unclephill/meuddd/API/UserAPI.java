package br.com.unclephill.meuddd.API;

import br.com.unclephill.meuddd.Object.UserObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {
    @POST("Cadastro/Cadastro")
    Call<UserObject> setUser(@Body UserObject userObject);
}
