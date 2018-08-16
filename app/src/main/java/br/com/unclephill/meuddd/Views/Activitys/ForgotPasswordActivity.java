package br.com.unclephill.meuddd.Views.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.unclephill.meuddd.API.API;
import br.com.unclephill.meuddd.App.FunctionsApp;
import br.com.unclephill.meuddd.Object.RecoverUserObject;
import br.com.unclephill.meuddd.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.unclephill.meuddd.App.FunctionsApp.closePgDialog;
import static br.com.unclephill.meuddd.App.FunctionsApp.modal;
import static br.com.unclephill.meuddd.App.FunctionsApp.showPgDialog;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText idEdtEmail;
    private Button idBtnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        this.inflar();
    }

    private void inflar(){
        this.idEdtEmail = (EditText) findViewById(R.id.idEdtEmailFP);
        this.idBtnEnviar = (Button) findViewById(R.id.idBtnEnviar);
    }

    public void onClickEnviar(View view){
        try{
            if (this.idEdtEmail.getText().toString().equals("")){
                modal(ForgotPasswordActivity.this,"Atenção!","Informe o Email!","OK");
                this.idEdtEmail.requestFocus();
                return;
            }

            showPgDialog(ForgotPasswordActivity.this);

            RecoverUserObject recoverUserObject = new RecoverUserObject();
            recoverUserObject.setEmail(this.idEdtEmail.getText().toString());
            this.setRecoverUserFromAPI(recoverUserObject);
        }catch (Exception ex){
            closePgDialog();
            Toast.makeText(ForgotPasswordActivity.this,"Ocorreu um erro: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickClose(View view){
        FunctionsApp.fecharActivity(ForgotPasswordActivity.this);
    }

    private void setRecoverUserFromAPI(RecoverUserObject recoverUserObject){
        API.setRecoverUser(recoverUserObject)
                .enqueue(new Callback<RecoverUserObject>() {
                    @Override
                    public void onResponse(Call<RecoverUserObject> call, Response<RecoverUserObject> response) {
                        closePgDialog();
                        if (response.isSuccessful()){
                            modal(ForgotPasswordActivity.this,
                                    "Atenção!","Seus dados de usuário foram enviados para o Email informado. " +
                                            "Por favor verifique sua caixa de entrada ou tente novamente.",
                                    "OK");
                        }else{
                            modal(ForgotPasswordActivity.this,
                                    "Atenção!",response.message(),
                                    "OK");
                        }
                    }

                    @Override
                    public void onFailure(Call<RecoverUserObject> call, Throwable t) {
                        closePgDialog();
                        modal(ForgotPasswordActivity.this,"Atenção!",
                                "Ocorreu um erro: " + t.getMessage() + ". Causa: " + t.getCause(),
                                "OK");
                    }
                });
    }

}
