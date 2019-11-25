package codenevisha.com.cleanarchitecture.presenter.detail

import android.os.Bundle
import codenevisha.com.cleanarchitecture.R
import codenevisha.com.cleanarchitecture.databinding.FragmentDetailBinding
import codenevisha.com.cleanarchitecture.domain.model.Article
import codenevisha.com.cleanarchitecture.presenter.base.BaseFragment
import codenevisha.com.cleanarchitecture.presenter.favourite.FavouriteFragment
import javax.inject.Inject

/**
 *
 *Created by EA for AndroidCleanArchitecture at 4/9/19
 *
 */
class DetailFragment @Inject constructor() : BaseFragment<DetailViewModel, FragmentDetailBinding>() {

    companion object {
        val CLASS_NAME = FavouriteFragment::class.java.simpleName
        private val TAG = FavouriteFragment::class.java.simpleName
        private const val EXTRA_KEY_ARTICLE = "extraKeyArticle"
    }


    @Inject
    lateinit var detailViewModel: DetailViewModel

    /**
     * default ViewModel of view, will be initialized by [viewModelFactory]
     */
    override lateinit var viewModel: DetailViewModel


    /**
     * Resource Id of main layout for view
     */
    override val layoutId = R.layout.fragment_detail

    lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = detailViewModel

        arguments?.getParcelable<Article>(EXTRA_KEY_ARTICLE)?.let {
            article = it
        }

    }

    override fun onViewInitialized(binding: FragmentDetailBinding) {
        super.onViewInitialized(binding)

        binding.viewmodel = viewModel
        binding.article = article


    }
}