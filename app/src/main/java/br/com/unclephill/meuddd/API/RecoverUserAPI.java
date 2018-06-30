package br.com.unclephill.meuddd.API;

import br.com.unclephill.meuddd.Object.RecoverUserObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RecoverUserAPI {
    @POST("Cadastro/Recuperar")
    Call<RecoverUserObject> setRecoverUser(@Body RecoverUserObject recoverUserObject);
}
