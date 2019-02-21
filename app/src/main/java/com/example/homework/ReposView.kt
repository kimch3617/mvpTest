package com.example.homework

import com.example.homework.base.BaseView
import com.example.homework.model.Repos
import com.example.homework.model.User

interface ReposView : BaseView {
    fun setUserProfile(user: User)
    fun setUserRepos(reposList: ArrayList<Repos>)
}