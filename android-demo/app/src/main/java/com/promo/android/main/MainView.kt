package com.promo.android.main


import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.promo.android.R
import com.promo.android.presentation.ReposAdapter
import data.repository.UserRepository
import domain.interactors.GetUserReposUseCase
import domain.model.Repo
import kotlinx.android.synthetic.main.view_main.*
import net.repository.UserSyncRepository

/**
 * Created by volodymyr.zaika on 2019-05-24 18:39. android
 */

class MainView : AppCompatActivity(), LifecycleOwner {
    private val vm = MainVM(GetUserReposUseCase(UserRepository(), UserSyncRepository()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_main)
        setViews()
    }


    override fun onResume() {
        super.onResume()
        vm.model.observe(this, Observer {
            pbLoader.visibility = View.GONE
            displayUser(it)
        })
        vm.error.observe(this, Observer {
            pbLoader.visibility = View.GONE
            Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()
        })
    }

    private fun setViews() {
        etUserLogin.setOnKeyListener { _, keyCode, action ->
            if (action.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                if (etUserLogin.text.toString().isBlank()) return@setOnKeyListener false

                vm.getUser(etUserLogin.text.toString())
                pbLoader.visibility = View.VISIBLE
                etUserLogin.setText("")

                true
            }
            false

        }
    }

    private fun displayUser(repos: List<Repo>) {
        var rvRepos = findViewById<RecyclerView>(R.id.rvRepos)
        rvRepos.layoutManager = LinearLayoutManager(baseContext)
        rvRepos.adapter = ReposAdapter(repos, baseContext)
        rvRepos.visibility = View.VISIBLE
    }

}