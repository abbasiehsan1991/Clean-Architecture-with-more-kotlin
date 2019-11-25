package codenevisha.com.cleanarchitecture.data

import codenevisha.com.cleanarchitecture.domain.model.ArticleModel

interface BaseCloudRepository {
       suspend fun getArticles(): ArticleModel
}