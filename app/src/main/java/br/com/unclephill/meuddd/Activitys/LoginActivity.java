package br.com.unclephill.meuddd.Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.unclephill.meuddd.API.API;
import br.com.unclephill.meuddd.Object.LoginObject;
import br.com.unclephill.meuddd.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.unclephill.meuddd.App.FunctionsApp.closePgDialog;
import static br.com.unclephill.meuddd.App.FunctionsApp.fecharActivity;
import static br.com.unclephill.meuddd.App.FunctionsApp.iniciarActivity;
import static br.com.unclephill.meuddd.App.FunctionsApp.modal;
import static br.com.unclephill.meuddd.App.FunctionsApp.showPgDialog;

public class LoginActivity extends AppCompatActivity{

    private EditText idEdtEmail;
    private EditText idEdtSenha;
    private Button idBtnLogin;
    private Button idBtnCadastrar;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.inflar();
    }

    private void inflar(){
        this.idEdtEmail = (EditText) findViewById(R.id.idEdtEmail);
        this.idEdtSenha = (EditText)  findViewById(R.id.idEdtSenha);
        this.idBtnLogin = (Button) findViewById(R.id.idBtnLogin);
        this.idBtnCadastrar =  (Button) findViewById(R.id.idBtnCadastrar);
    }

    public void onClickLogin(View view){
        try{
            if (this.idEdtEmail.getText().toString().equals("")){
                modal(LoginActivity.this,"Atenção!","Informe o Email do usuário!","OK");
                this.idEdtEmail.requestFocus();
                return;
            }

            if (this.idEdtSenha.getText().toString().equals("")){
                modal(LoginActivity.this,"Atenção!","Informe a senha!","OK");
                this.idEdtSenha.requestFocus();
                return;
            }

            showPgDialog(LoginActivity.this);

            LoginObject loginObject = new LoginObject();
            loginObject.setEmail(this.idEdtEmail.getText().toString());
            loginObject.setSenha(this.idEdtSenha.getText().toString());
            this.setLoginFromAPI(loginObject);
        }catch (Exception ex){
            closePgDialog();
            Toast.makeText(LoginActivity.this,"Ocorreu um erro: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickCadastrar(View view){
        iniciarActivity(LoginActivity.this,UserActivity.class,null);
    }

    public void onClickForgotPassword(View view){
        iniciarActivity(LoginActivity.this,ForgotPasswordActivity.class,null);
    }

    private void setLoginFromAPI(LoginObject loginObject){
        API.setLogin(loginObject)
                .enqueue(new Callback<LoginObject>() {
                    @Override
                    public void onResponse(Call<LoginObject> call, Response<LoginObject> response) {
                        closePgDialog();
                        if (response.isSuccessful()){
                            iniciarActivity(LoginActivity.this,MenuActivity.class,null);
                            fecharActivity(LoginActivity.this);
                        }else{
                            modal(LoginActivity.this,
                                    "Atenção!",response.message(),
                                    "OK");
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginObject> call, Throwable t) {
                        closePgDialog();
                        modal(LoginActivity.this,"Atenção!",
                                "Ocorreu um erro: " + t.getMessage() + ". Causa: " + t.getCause(),
                                "OK");
                    }
                });
    }
}
