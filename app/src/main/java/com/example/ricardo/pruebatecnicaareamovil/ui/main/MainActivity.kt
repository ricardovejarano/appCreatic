package com.example.ricardo.pruebatecnicaareamovil.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import buildViewModel
import com.example.ricardo.pruebatecnicaareamovil.R
import com.example.ricardo.pruebatecnicaareamovil.databinding.ActivityMainBinding
import com.example.ricardo.pruebatecnicaareamovil.di.Injectable
import com.example.ricardo.pruebatecnicaareamovil.util.LifeDisposable
import com.example.ricardo.pruebatecnicaareamovil.util.subscribeByAction
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import text
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Injectable {

    private var username = ""

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    val viewModel: MainViewModel by lazy { buildViewModel<MainViewModel>(factory) }

    val dis: LifeDisposable = LifeDisposable(this)

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        dis add mainBtnSearch.clicks()
                .flatMap {
                    username = mainEdtTxtSearch.text()
                    viewModel.getUser(username)
                }
                .subscribeByAction(
                        onNext = { user ->
                            binding.user = user
                        },
                        onError = { toast(it.message!!)},
                        onHttpError = { toast(it)}
                )
    }
}
