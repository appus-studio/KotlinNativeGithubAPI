package data.net

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.takeFrom


object NetClient {
    const val BASE_URL = "https://api.github.com/"
    val client = HttpClient()

    fun HttpRequestBuilder.apiUrl(path: String, userId: String? = null) {
        if (userId != null) {
            header(HttpHeaders.Authorization, "Bearer $userId")
        }

        url {
            this.takeFrom(NetClient.BASE_URL)
            encodedPath = path
        }
    }

}



