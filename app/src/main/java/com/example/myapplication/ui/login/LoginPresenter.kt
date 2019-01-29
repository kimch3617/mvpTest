package com.example.myapplication.ui.login

import com.example.myapplication.MyApplication
import com.example.myapplication.base.BasePresenter
import com.example.myapplication.model.RestResponse
import com.example.myapplication.repository.RemoteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter : BasePresenter<LoginView>() {
    fun login(nickname: String, password: String) {
        RemoteRepository.create().login(nickname, password).enqueue(object : Callback<RestResponse> {
            override fun onResponse(call: Call<RestResponse>, response: Response<RestResponse>) {
                response.body()?.let { login ->
                    if (login.ok) getView()?.setLogin()
                    else getView()?.showToast(login.errorMsg)
                }
            }

            override fun onFailure(call: Call<RestResponse>, t: Throwable) {
            }
        })
    }
}