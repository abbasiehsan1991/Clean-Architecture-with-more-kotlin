package codenevisha.com.cleanarchitecture.presenter.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnakeBar(message:String){
    Snackbar.make(this , message , Snackbar.LENGTH_SHORT).show()
}