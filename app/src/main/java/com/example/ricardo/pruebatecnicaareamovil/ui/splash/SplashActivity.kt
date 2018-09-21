package com.example.ricardo.pruebatecnicaareamovil.ui.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.ricardo.pruebatecnicaareamovil.R
import com.example.ricardo.pruebatecnicaareamovil.ui.main.MainActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    private val timer = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity<MainActivity>()
            finish()
        }, timer.toLong())

    }
}
