package common.domain.interactors

import domain.interactors.GetUserReposUseCase

abstract class UseCase<Params, Result> {
    abstract suspend fun execute(params: Params): Result
    abstract fun executeSync(login: String, completion: (String)->Unit): Result

}