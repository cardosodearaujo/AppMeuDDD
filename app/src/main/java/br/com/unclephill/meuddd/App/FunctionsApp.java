package br.com.unclephill.meuddd.App;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class FunctionsApp {
    public static void iniciarActivity(Context context, Class classe, Bundle paramentros){
        Intent intent = new Intent(context,classe);
        if (paramentros != null){intent.putExtras(paramentros);}
        context.startActivity(intent);
    }

    public static void fecharActivity(Context context){
        ((Activity) context).finish();
    }
}
