package codenevisha.com.cleanarchitecture.presenter.home

import androidx.lifecycle.MutableLiveData
import codenevisha.com.cleanarchitecture.domain.model.Article
import codenevisha.com.cleanarchitecture.domain.model.ArticleModel
import codenevisha.com.cleanarchitecture.domain.model.ErrorModel
import codenevisha.com.cleanarchitecture.domain.usecase.article.GetArticleListUseCase
import codenevisha.com.cleanarchitecture.domain.usecase.base.SingleUseCase
import codenevisha.com.cleanarchitecture.presenter.base.BaseViewModel
import codenevisha.com.cleanarchitecture.presenter.util.ELog
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val getArticleListUseCase: GetArticleListUseCase
) : BaseViewModel() {
    companion object {
        val TAG = HomeViewModel::class.java.simpleName
    }

    val articles = MutableLiveData<MutableList<Article>>()
    val errorModel = MutableLiveData<ErrorModel>()

    init {
        empty.value = true
        isLoadingData.value = true
    }

    override fun onStart() {

        getArticleListUseCase.execute(this::articleResponse, TokenExpired())
    }


    private fun articleResponse(response: SingleUseCase.ResultHandler<ArticleModel>) {

        response.apply {

            onSuccess {

                ELog.print(ELog.Level.D, TAG, "RESPONSE size [${it.articles?.size}]")

                empty.value = it.articles.isNullOrEmpty()!!
                isLoadingData.value  = false

                it.articles.let { results ->
                    articles.value = results
                }

            }

            onError { errors ->
                mSnackBarText.value = errors.message
            }

            onCancel {
                mSnackBarText.value = it.message
            }

        }

    }
}