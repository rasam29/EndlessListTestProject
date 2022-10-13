package com.example.endlessproject.tools

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


@BindingAdapter("isShowing")
fun setVisibility(view: View, isShowing: Boolean) {
    view.visibility = if (isShowing) View.VISIBLE else View.GONE
}

@BindingAdapter("loadIcon")
fun loadIcon(image: ImageView, url: String?) {
    Picasso.get()
        .load(url)
        .resize(50, 50)
        .centerCrop()
        .into(image)
}