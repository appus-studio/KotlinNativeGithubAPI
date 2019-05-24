package domain.interactors

import common.domain.interactors.UseCase
import domain.repository.Repository

import kotlinx.coroutines.*
import net.repository.UserSyncRepository


class GetUserReposUseCase(
    private val mUserRepository: Repository,
    private val mUserSyncRepository: UserSyncRepository
) : UseCase<GetUserReposUseCase.Params, String>() {

    override suspend fun execute(params: Params): String = mUserRepository.getUserData(params.login)

    override fun executeSync(login: String, completion: (String) -> Unit): String =
        mUserSyncRepository.getUserRepositories(login, completion)

    data class Params(val login: String)
}

