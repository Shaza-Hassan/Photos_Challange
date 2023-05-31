package com.shaza.photoschallange.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Created by Shaza Hassan on 31/May/2023.
 */

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) { // This methods should not have any return type, = declaration would make it return that object declaration.

    if (url != null){
        Glide.with(view.context).load(url).into(view)
    }
}