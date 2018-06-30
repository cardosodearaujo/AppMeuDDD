package br.com.unclephill.meuddd.API;

import java.util.List;

import br.com.unclephill.meuddd.Object.DDDCityObject;
import br.com.unclephill.meuddd.Object.LoginObject;
import br.com.unclephill.meuddd.Object.RecoverUserObject;
import br.com.unclephill.meuddd.Object.UserObject;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    private static final Retrofit RETROFIT = new Retrofit
            .Builder()
            .baseUrl("http://hiruke.ddns.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Call<UserObject> setUser(UserObject userObject) {
        UserAPI userAPI = RETROFIT.create(UserAPI.class);
        return userAPI.setUser(userObject);
    }

    public static Call<RecoverUserObject> setRecoverUser(RecoverUserObject recoverUserObject) {
        RecoverUserAPI recoverUserAPI = RETROFIT.create(RecoverUserAPI.class);
        return recoverUserAPI.setRecoverUser(recoverUserObject);
    }

    public static Call<LoginObject> setLogin(LoginObject loginObject) {
        LoginAPI loginAPI = RETROFIT.create(LoginAPI.class);
        return loginAPI.setLogin(loginObject);
    }

    public static Call<List<DDDCityObject>> getCidades(DDDCityObject dddCityObject) {
        CityAPI cityAPI = RETROFIT.create(CityAPI.class);
        return cityAPI.getCidades(dddCityObject);
    }

    public static Call<List<DDDCityObject>> getDDD(DDDCityObject dddCityObject) {
        DDDAPI cityAPI = RETROFIT.create(DDDAPI.class);
        return cityAPI.getDDD(dddCityObject);
    }
}
