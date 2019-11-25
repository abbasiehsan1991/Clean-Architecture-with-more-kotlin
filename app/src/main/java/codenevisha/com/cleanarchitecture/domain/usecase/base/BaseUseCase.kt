package codenevisha.com.cleanarchitecture.domain.usecase.base

abstract class BaseUseCase<T> {

    abstract suspend fun backgroundExecute():T

}