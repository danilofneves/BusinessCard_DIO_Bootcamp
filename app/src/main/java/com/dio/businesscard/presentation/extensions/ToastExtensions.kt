package com.dio.businesscard.presentation.extensions

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Activity.toast(message: String) {
    Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message: String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}