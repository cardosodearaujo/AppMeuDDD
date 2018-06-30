package br.com.unclephill.meuddd.Views.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.unclephill.meuddd.API.API;
import br.com.unclephill.meuddd.Object.UserObject;
import br.com.unclephill.meuddd.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.unclephill.meuddd.App.FunctionsApp.closePgDialog;
import static br.com.unclephill.meuddd.App.FunctionsApp.fecharActivity;
import static br.com.unclephill.meuddd.App.FunctionsApp.modal;
import static br.com.unclephill.meuddd.App.FunctionsApp.showPgDialog;

public class UserActivity extends AppCompatActivity {

    private EditText idEdtNome;
    private EditText idEdtEmail;
    private EditText idEdtSenha;
    private Button idBtnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        this.inflar();
    }

    private void inflar(){
        this.idEdtNome = (EditText) findViewById(R.id.idEdtNome);
        this.idEdtEmail = (EditText) findViewById(R.id.idEdtEmail);
        this.idEdtSenha = (EditText) findViewById(R.id.idEdtSenha);
        this.idBtnCadastrar = (Button) findViewById(R.id.idBtnCadastrar);
    }

    public void onClickCadastrar(View view){
        try{
            if (this.idEdtNome.getText().toString().equals("")){
                modal(UserActivity.this,"Atenção!","Informe o nome do usuário!","OK");
                this.idEdtNome.requestFocus();
                return;
            }

            if (this.idEdtEmail.getText().toString().equals("")){
                modal(UserActivity.this,"Atenção!","Informe o Email do usuário!","OK");
                this.idEdtEmail.requestFocus();
                return;
            }

            if (this.idEdtSenha.getText().toString().equals("")){
                modal(UserActivity.this,"Atenção!","Informe a senha!","OK");
                this.idEdtSenha.requestFocus();
                return;
            }

            showPgDialog(UserActivity.this);

            UserObject userObject = new UserObject();
            userObject.setNome(this.idEdtNome.getText().toString());
            userObject.setEmail(this.idEdtEmail.getText().toString());
            userObject.setSenha(this.idEdtSenha.getText().toString());

            this.setUserFromAPI(userObject);
        }catch (Exception ex){
            closePgDialog();
            Toast.makeText(UserActivity.this,"Ocorreu um erro: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public  void onClickClose(View view){
        fecharActivity(UserActivity.this);
    }

    private void setUserFromAPI(UserObject userObject){
        API.setUser(userObject)
                .enqueue(new Callback<UserObject>() {
                    @Override
                    public void onResponse(Call<UserObject> call, Response<UserObject> response) {
                        closePgDialog();
                        if (response.isSuccessful()){
                            Toast.makeText(UserActivity.this,"Usuário cadastrado!",Toast.LENGTH_LONG).show();
                            fecharActivity(UserActivity.this);
                        }else{
                            modal(UserActivity.this,
                                    "Atenção!",response.message(),
                                    "OK");
                        }
                    }

                    @Override
                    public void onFailure(Call<UserObject> call, Throwable t) {
                        closePgDialog();
                        modal(UserActivity.this,"Atenção!",
                                "Ocorreu um erro: " + t.getMessage() + ". Causa: " + t.getCause(),
                                "OK");
                    }
                });
    }
}
