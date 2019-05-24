package net.repository

import data.net.APIs
import io.ktor.client.HttpClient
import io.ktor.client.call.call
import io.ktor.client.response.readText
import io.ktor.http.HttpMethod
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

private class MainDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        block.run()
    }
}

internal class MainScope: CoroutineScope {
    private val dispatcher = MainDispatcher()
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatcher + job


}

class UserSyncRepository {
    private val client = HttpClient()

    internal suspend fun request(urlString: String): String {
        val result: String = client.call(urlString) {
            method = HttpMethod.Get
        }.response.readText()
        return result
    }

    fun getUserRepositories(login: String, completion: (String) -> Unit) :String{
        MainScope().launch {
            val result = request("https://api.github.com${APIs.USER}$login${APIs.REPOS}")
            completion(result)
        }
        return ""
    }
}