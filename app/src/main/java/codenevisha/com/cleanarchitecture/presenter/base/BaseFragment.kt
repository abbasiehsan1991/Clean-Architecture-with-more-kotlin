package codenevisha.com.cleanarchitecture.presenter.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import codenevisha.com.cleanarchitecture.di.builder.ViewModelFactory
import codenevisha.com.cleanarchitecture.presenter.util.showSnakeBar
import dagger.android.support.DaggerFragment
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseFragment<V : BaseViewModel, B : ViewDataBinding> :
    DaggerFragment(),
    BaseView<V, B>,
    HasSupportFragmentInjector {

    override lateinit var binding: B

    @Inject
    override lateinit var viewModelFactory: ViewModelFactory

    /*override val viewModel: V by lazy {
        @Suppress("UNCHECKED_CAST")
        ViewModelProviders.of(this, viewModelFactory).get(
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<V>
        )
    }
*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        binding.setLifecycleOwner(this)
        lifecycle.addObserver(viewModel)
        viewModel.onStart()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onViewInitialized(binding)

        setupSnakbar()

    }

    private fun setupSnakbar() {

        viewModel.mSnackBarText.observe(this , Observer {

            binding.root.showSnakeBar(it)

        })
    }
}