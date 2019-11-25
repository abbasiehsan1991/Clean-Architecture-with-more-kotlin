package codenevisha.com.cleanarchitecture.presenter.home

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import codenevisha.com.cleanarchitecture.R
import codenevisha.com.cleanarchitecture.databinding.FragmentHomeBinding
import codenevisha.com.cleanarchitecture.domain.model.Article
import codenevisha.com.cleanarchitecture.presenter.MainNavigator
import codenevisha.com.cleanarchitecture.presenter.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment @Inject constructor() :
    BaseFragment<@Inject HomeViewModel, FragmentHomeBinding>(),
    HomeAdapterListener {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    @Inject
    lateinit var mainNavigation: MainNavigator

    /**
     * default ViewModel of view, will be initialized by [viewModelFactory]
     */
    override lateinit var viewModel: HomeViewModel

    companion object {
        val CLASS_NAME = HomeFragment::class.java.simpleName
        private val TAG = HomeFragment::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = homeViewModel
    }

    /**
     * Resource Id of main layout for view
     */
    override val layoutId: Int = R.layout.fragment_home

    override fun onViewInitialized(binding: FragmentHomeBinding) {
        super.onViewInitialized(binding)

        binding.viewmodel = viewModel
        viewModel.onStart()

        prepareRvHome()

        Log.d(TAG, homeViewModel.toString())
        Log.d(TAG, viewModel.toString())
    }

    private fun prepareRvHome() {

        rv_main_articles.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_main_articles.adapter = HomeAdapter(this)
        rv_main_articles.hasFixedSize()

    }

    override fun onArticleClickListener(article: Article) {

        activity?.let {
            mainNavigation.showDetailFragment(
                article,
                R.id.fragment_container,
                it.supportFragmentManager
            )
        }

    }
}
