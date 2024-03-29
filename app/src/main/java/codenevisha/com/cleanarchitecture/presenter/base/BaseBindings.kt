package codenevisha.com.cleanarchitecture.presenter.base

import androidx.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import androidx.constraintlayout.widget.Placeholder
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import codenevisha.com.cleanarchitecture.presenter.home.HomeAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions


/**
 * Contains [BindingAdapter]s for the [T] list or
 * Setting images into an [ImageView]
 */
class BaseBindings {

    companion object {
        private val TAG = "ListBindings"

        @JvmStatic
        @BindingAdapter("app:adapter")
        fun <T> setItems(recyclerView: RecyclerView, items: List<T>?) {

            Log.d(TAG, "articles:[$items]")

            if (recyclerView.adapter is HomeAdapter) {

                items?.let {

                    (recyclerView.adapter as HomeAdapter).swapData(it)

                }

            }
        }

        @JvmStatic
        @BindingAdapter("app:loadImage", "app:placeHolder", requireAll = false)
        fun setImageResource(view: ImageView, imageUrl: String?, placeholder: Drawable?) {

            val context = view.context

            val options = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

            placeholder?.let { options.placeholder(placeholder) }



            imageUrl?.let {

                Glide.with(context)
                    .setDefaultRequestOptions(options)
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade(1000))
                    .into(view)
            }
        }

    }
}
