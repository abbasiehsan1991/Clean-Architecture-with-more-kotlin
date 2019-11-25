package codenevisha.com.cleanarchitecture.domain.usecase.base

import codenevisha.com.cleanarchitecture.data.CloudErrorMapper
import codenevisha.com.cleanarchitecture.domain.model.ErrorModel
import kotlinx.coroutines.*


typealias completeBlock<T> = SingleUseCase.ResultHandler<T>.() -> Unit

abstract class SingleUseCase<T>(private val cloudErrorMapper: CloudErrorMapper) : BaseUseCase<T>() {

    private val TAG = SingleUseCase::class.java.simpleName

    private lateinit var job: Job

    fun execute(
        block: completeBlock<T>,
        onTokenExpire: (() -> Unit)? = null
    ) {

        val response = ResultHandler<T>().apply { block() }

        CoroutineScope(Dispatchers.Main).launch {

            try {

                val result = withContext(Dispatchers.IO) {
                    backgroundExecute()
                }

                response.invoke(result)

            } catch (cancellationException: CancellationException) {
                response.invoke(cancellationException)

            } catch (error: Exception) {
                val errorModel = cloudErrorMapper.mapToDomainErrorException(error)
                response.invoke(errorModel)
            }
        }

    }

    class ResultHandler<T> {
        private var onSuccess: ((T) -> Unit)? = null
        private var onError: ((ErrorModel) -> Unit)? = null
        private var onCancel: ((CancellationException) -> Unit)? = null

        fun onSuccess(block: (T) -> Unit) {
            onSuccess = block
        }

        fun onError(block: (ErrorModel) -> Unit) {

            onError = block

        }

        fun onCancel(block: (CancellationException) -> Unit) {
            onCancel = block
        }


        fun invoke(result: T) {
            onSuccess?.invoke(result)
        }

        fun invoke(error: ErrorModel) {
            onError?.invoke(error)
        }

        fun invoke(error: CancellationException) {
            onCancel?.invoke(error)
        }
    }

}