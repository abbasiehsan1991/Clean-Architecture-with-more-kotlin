package codenevisha.com.cleanarchitecture.data.source.cloud

import codenevisha.com.cleanarchitecture.domain.model.ArticleModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface ApiService {
    @GET("top-headlines?sources=techcrunch&apiKey=05ec1c5d916f4d379ea1ec90ed06dd09")
    fun getArticlesAsync()
            : Deferred<ArticleModel>

}