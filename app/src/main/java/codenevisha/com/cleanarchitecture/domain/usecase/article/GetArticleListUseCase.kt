package codenevisha.com.cleanarchitecture.domain.usecase.article

import codenevisha.com.cleanarchitecture.data.CloudErrorMapper
import codenevisha.com.cleanarchitecture.domain.model.ArticleModel
import codenevisha.com.cleanarchitecture.domain.repository.AppRepository
import codenevisha.com.cleanarchitecture.domain.usecase.base.SingleUseCase
import javax.inject.Inject

class GetArticleListUseCase @Inject constructor(

    private val errorUtil: CloudErrorMapper,
    private val appRepository: AppRepository //AppRepository interface

) : SingleUseCase<ArticleModel>(errorUtil) {

    override suspend fun backgroundExecute(): ArticleModel {
        return appRepository.getArticles()
    }
}