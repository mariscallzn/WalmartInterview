package com.andymariscal.walmartproducts.domain

import androidx.lifecycle.MediatorLiveData
import io.reactivex.disposables.Disposable
import com.andymariscal.walmartproducts.result.Result

abstract class UseCase <in P, R> {
    protected val result = MediatorLiveData<Result<R>>()

    // Make this as open so that mock instances can mock this method
    open fun observe(): MediatorLiveData<Result<R>> {
        return result
    }

    abstract fun execute(parameter: P)
}

abstract class RxUseCase<in P, R> : UseCase<P, R>() {
    lateinit var disposable: Disposable

    fun dispose() {
        if(!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}