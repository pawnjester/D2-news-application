package com.andela.d2_news_application.utils

import android.content.Context
import android.databinding.BindingAdapter
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.andela.d2_news_application.R
import com.andela.d2_news_application.model.MultimediaItem
import com.squareup.picasso.Picasso


fun Context.showToast(message: String) = Toast.makeText(
        this, message, Toast.LENGTH_LONG).show()

fun View.show(){
    visibility = View.VISIBLE
}

fun View.dontShow() {
    visibility = View.GONE
}

fun ViewGroup.showSnackbar(message: String) = Snackbar
        .make(this, message, Snackbar.LENGTH_LONG).show()


@BindingAdapter("formattedDate")
fun formatDate(view: TextView?, date: String?) {
    val splittedDate = date?.split("T")!!.component1()
    val furtherSplittedDate = splittedDate.split("-")
    val day = furtherSplittedDate.component3()
    val year = furtherSplittedDate.component1()
    var dateText = ""
    val extra  = "th"
    when(furtherSplittedDate.component2()) {
        "1" -> dateText = "January"
        "2" -> dateText ="February"
        "3" -> dateText ="March"
        "4" -> dateText ="April"
        "5" -> dateText ="May"
        "6" -> dateText ="June"
        "7" -> dateText ="July"
        "8" -> dateText ="August"
        "9" -> dateText ="September"
        "10" -> dateText ="October"
        "11" -> dateText ="November"
        "12" -> dateText ="December"
    }
    view?.text = "$day$extra of $dateText, $year"
}

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView?, imageUrl: List<MultimediaItem>?) {
    val image = imageUrl
    image?.let {
        for (selectedImage in it) {
            val url = selectedImage.url ?: ""
            if (url.isEmpty()) {
                Picasso.get().load(R.drawable.ic_hourglass_empty_black_24dp).into(view)
            } else {
                Picasso.get().load(url).fit().into(view)
            }
        }
    }
}