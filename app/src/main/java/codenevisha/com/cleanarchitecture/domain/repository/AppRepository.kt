package codenevisha.com.cleanarchitecture.domain.repository

import codenevisha.com.cleanarchitecture.data.source.db.AppDatabase
import codenevisha.com.cleanarchitecture.domain.model.Article
import codenevisha.com.cleanarchitecture.domain.model.ArticleModel

interface AppRepository {

    /**
     * Return list of [Article] for available resource
     */
    suspend fun getArticles():ArticleModel

    /**
     * Return [Article] form database with received [id]
     * If found it
     */
    suspend  fun getArticleByIdFromDB(articleId:Int):Article?

    /**
     * Delete received [Article]
     * If is available in database
     */
    suspend  fun deleteArticleFromDB(article:Article)

    /**
     * Delete all available [Article] (s) in [AppDatabase]
     */
    suspend  fun deleteAllArticleFromDB()

    /**
     * Update received [Article]
     * If it's available in [AppDatabase]
     */
    suspend  fun updateArticleInDB(article: Article)
}