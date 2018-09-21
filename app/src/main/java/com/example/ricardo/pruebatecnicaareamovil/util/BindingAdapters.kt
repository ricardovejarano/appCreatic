package com.example.ricardo.pruebatecnicaareamovil.util

import android.databinding.BindingAdapter
import android.net.Uri
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("app:loadImg")
fun setImage(img: CircleImageView, url: String?){
    if (url != null)
        Picasso.get()
                .load(Uri.parse(url))
                .into(img)
}
