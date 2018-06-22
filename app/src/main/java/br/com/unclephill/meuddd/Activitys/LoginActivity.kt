package br.com.unclephill.meuddd.Activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.unclephill.meuddd.App.FunctionsApp
import br.com.unclephill.meuddd.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun onClickForgotPassword(view: View?){
        FunctionsApp.iniciarActivity(this@LoginActivity,ForgotPasswordActivity::class.java,null)
    }

    fun onClickCadastrar(view: View?){
        FunctionsApp.iniciarActivity(this@LoginActivity, UserActivity::class.java,null)
    }
}
