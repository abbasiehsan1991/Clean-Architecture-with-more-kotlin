package codenevisha.com.cleanarchitecture.data

import codenevisha.com.cleanarchitecture.data.source.cloud.ApiService
import codenevisha.com.cleanarchitecture.domain.model.ArticleModel
import javax.inject.Inject

class CloudRepository @Inject constructor(val api: ApiService): BaseCloudRepository {

    override suspend fun getArticles(): ArticleModel {
    return  api.getArticlesAsync().await()
    }
}