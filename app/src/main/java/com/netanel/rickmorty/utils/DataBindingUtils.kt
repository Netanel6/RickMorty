package com.netanel.rickmorty.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


/**
 * Created by netanelamar on 11/01/2024.
 * NetanelCA2@gmail.com
 */
object DataBindingUtils {

    @BindingAdapter("bind:imageUrl")
    @JvmStatic
    fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }

    @BindingAdapter("bind:civ_border_color")
    @JvmStatic
    fun setBorderColor(view: CircleImageView, color: Int) {
        view.borderColor = ContextCompat.getColor(view.context, color)
    }

    @BindingAdapter("bind:srcImg")
    @JvmStatic
    fun setSrcImg(view: ImageView, source: Int) {
        view.setImageResource(source)
    }

}