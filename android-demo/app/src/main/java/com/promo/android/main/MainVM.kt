package com.promo.android.main

import androidx.lifecycle.MutableLiveData
import com.promo.android.common.base.BaseVM
import domain.interactors.GetUserReposUseCase
import domain.model.Repo
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by volodymyr.zaika on 2019-05-24 18:41. android
 */

class MainVM(private val mGetUserUseCase: GetUserReposUseCase) : BaseVM() {
    internal val model = MutableLiveData<List<Repo>>()
    internal val error = MutableLiveData<String>()

    fun getUser(login: String) {
        launchAsyncOnError(
            {
                val response = mGetUserUseCase.execute(GetUserReposUseCase.Params(login))
                model.value = JSONArray(response).run {
                    var result = ArrayList<Repo>()
                    for (i in 0..this.length()-1) {
                        var jsonObject = JSONObject(this.get(i).toString())
                        result.add(
                            Repo(
                                id = jsonObject.getInt("id"),
                                name = jsonObject.getString("name"),
                                description = jsonObject.getString("description"),
                                url = jsonObject.getString("html_url")
                            )
                        )
                    }
                    return@run result
                }
            },
            {
                error.value = it?.message ?: "Error"
            }
        )
    }
}