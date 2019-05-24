package data.repository

import data.net.APIs
import data.net.NetClient
import data.net.NetClient.apiUrl
import domain.repository.Repository
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers

class UserRepository : Repository {
    override suspend fun getUserData(login: String): String =
        NetClient.client.get {
            apiUrl("${APIs.USER}$login${APIs.REPOS}")
        }


}

