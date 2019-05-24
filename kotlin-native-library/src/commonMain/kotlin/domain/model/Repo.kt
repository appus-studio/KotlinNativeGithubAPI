package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Repo(
    var id: Int? = 0,
    var name: String? = "",
    var description: String? = "",
    var url: String? = ""
)
