package com.promo.android.common.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.promo.android.common.SingleLiveEvent
import kotlinx.coroutines.*

/**
 * Created by bogdan.martynov on 2019-05-16 13:00. android
 */

abstract class BaseVM : ViewModel(), LifecycleObserver {
    val isLoading = SingleLiveEvent<Boolean>()
    private val asyncJobs = mutableListOf<Job>()

    protected fun launchAsync(block: suspend CoroutineScope.() -> Unit) {
        val job: Job = GlobalScope.launch(Dispatchers.Main) { block() }
        asyncJobs.add(job)
        job.invokeOnCompletion { asyncJobs.remove(job) }
    }

    protected fun launchAsyncOnError(
        block: suspend CoroutineScope.() -> Unit,
        onError: ((error: Exception?) -> Unit)? = null
    ) {
        isLoading.value = true
        val job: Job = GlobalScope.launch(Dispatchers.Main) {
            try {
                block()
            } catch (e: Exception) {
                onError?.invoke(e)
            } finally {
                isLoading.value = false
            }
        }
        asyncJobs.add(job)
        job.invokeOnCompletion { asyncJobs.remove(job) }
    }
}