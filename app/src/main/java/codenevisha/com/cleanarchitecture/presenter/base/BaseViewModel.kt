package codenevisha.com.cleanarchitecture.presenter.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import codenevisha.com.cleanarchitecture.presenter.util.ELog

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    val isLoadingData = MutableLiveData<Boolean>()
    val empty = MutableLiveData<Boolean>()
    val mSnackBarText = MutableLiveData<String>()

    companion object {
        private val TAG = BaseViewModel::class.java.simpleName.toString()
    }

    abstract fun onStart()

    override fun onCleared() {
        super.onCleared()
    }


    /**
     * This will call if we find it out that the user token is expired
     * There is the place that we can do anything that we want by a user with
     * Expired token
     */
    class TokenExpired : () -> Unit {
        override fun invoke() {
            ELog.print(ELog.Level.D , TAG , "User's token is expired.")
        }
    }
}