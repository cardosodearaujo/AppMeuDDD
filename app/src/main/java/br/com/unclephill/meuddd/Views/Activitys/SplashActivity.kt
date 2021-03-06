package br.com.unclephill.meuddd.Views.Activitys

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.unclephill.meuddd.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val it = Intent(this@SplashActivity, MenuActivity::class.java)
            this@SplashActivity.startActivity(it)
            this@SplashActivity.finish()
        }, 1000)
    }
}
