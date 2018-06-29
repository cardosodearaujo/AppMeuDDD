package br.com.unclephill.meuddd.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.unclephill.meuddd.App.FunctionsApp;
import br.com.unclephill.meuddd.R;
import kotlin.Function;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    public  void onClickClose(View view){
        FunctionsApp.fecharActivity(UserActivity.this);
    }

    public void onClickCadastrar(View view){
        
    }
}
