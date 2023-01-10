package com.kunal.movie.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun Context.loadImage(imageView: ImageView, path: String) {
    Glide.with(this).load("https://image.tmdb.org/t/p/original/${path}").into(imageView)
}