package domain.repository

interface Repository {
  suspend fun getUserData(login: String): String
}