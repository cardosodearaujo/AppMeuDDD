package br.com.unclephill.meuddd.App;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import br.com.unclephill.meuddd.Object.LoginObject;
import br.com.unclephill.meuddd.Object.UserObject;

public class FunctionsApp {
    public static void iniciarActivity(Context context, Class classe, Bundle paramentros){
        Intent intent = new Intent(context,classe);
        if (paramentros != null){intent.putExtras(paramentros);}
        context.startActivity(intent);
    }

    public static void fecharActivity(Context context){
        ((Activity) context).finish();
    }

    public static ProgressDialog PG_DIALOG;

    public static LoginObject User = new LoginObject();

    public static void showPgDialog(Context context){
        PG_DIALOG = new ProgressDialog(context);
        PG_DIALOG.setMessage("Aguarde...");
        PG_DIALOG.setIndeterminate(false);
        PG_DIALOG.setCancelable(false);
        PG_DIALOG.setProgress(0);
        PG_DIALOG.show();
    }

    public static void closePgDialog(){
        if (PG_DIALOG.isShowing()){ PG_DIALOG.dismiss();}
    }

    public static AlertDialog modal(Context context, String titulo, String menssagem, String mensagemBotao){
        return new AlertDialog.Builder(context)
                .setTitle(titulo)
                .setMessage(menssagem)
                .setNeutralButton(mensagemBotao, null).show();
    }

    public static void iniciarFragment(Fragment fragment, int id, FragmentManager fragmentManager){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id,fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    public static void closeKeyboard(Context context, View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeybord(Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }

}
