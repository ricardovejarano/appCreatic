package com.example.ricardo.pruebatecnicaareamovil.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ricardo.pruebatecnicaareamovil.R
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBtnSearch.clicks()
                .subscribe{

                }
    }
}
