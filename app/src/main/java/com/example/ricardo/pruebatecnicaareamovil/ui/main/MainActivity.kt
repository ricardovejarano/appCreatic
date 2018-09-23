package com.example.ricardo.pruebatecnicaareamovil.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import buildViewModel
import com.example.ricardo.pruebatecnicaareamovil.R
import com.example.ricardo.pruebatecnicaareamovil.data.model.User
import com.example.ricardo.pruebatecnicaareamovil.data.model.UserRealm
import com.example.ricardo.pruebatecnicaareamovil.databinding.ActivityMainBinding
import com.example.ricardo.pruebatecnicaareamovil.di.Injectable
import com.example.ricardo.pruebatecnicaareamovil.util.BlurImage
import com.example.ricardo.pruebatecnicaareamovil.util.LifeDisposable
import com.example.ricardo.pruebatecnicaareamovil.util.subscribeByAction
import com.jakewharton.rxbinding2.view.clicks
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import text
import java.lang.Exception
import javax.inject.Inject


class MainActivity : AppCompatActivity(), Injectable {

    private var username = ""

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    val viewModel: MainViewModel by lazy { buildViewModel<MainViewModel>(factory) }

    val dis: LifeDisposable = LifeDisposable(this)

    lateinit var binding: ActivityMainBinding

    val realm: Realm by lazy { Realm.getDefaultInstance() }

    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dbUser =
                realm.where<UserRealm>()
                        .findFirst()

        if(dbUser != null){
            user = User(dbUser.url, dbUser.name)
            binding.user = user
            loadBluri()
        }
    }

    // Bluri
    fun loadBluri() {
        val target = object : Target {
            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                profileRibbon.setImageResource(R.mipmap.ic_launcher)
            }

            override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                profileRibbon.setImageBitmap(BlurImage.fastblur(bitmap, 1f, 35))
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable) {

            }
        }

        profileRibbon.tag = target
        Picasso.get()
                .load(user.avatar_url)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(target)
    }

    override fun onResume() {
        super.onResume()

        mainBtnClear.clicks()
                .subscribe {
                    mainEdtTxtSearch.setText("")
                }

        dis add mainBtnSearch.clicks()
                .flatMap {
                    username = mainEdtTxtSearch.text()
                    viewModel.getUser(username)
                }
                .subscribeByAction(
                        onNext = { obtained ->
                            user = obtained
                            binding.user = user
                            realm.executeTransactionAsync({realm ->
                                realm.deleteAll()
                                val newUser = realm.createObject<UserRealm>(0)
                                newUser.name = user.name ?: "No hay nombre registrado"
                                newUser.url = user.avatar_url ?: ""
                            },{
                                loadBluri()
                                // toast("exito")
                            },{
                                toast(it.message!!)
                            })

                        },
                        onError = { toast(it.message!!)},
                        onHttpError = { toast(it)}
                )
    }
}
