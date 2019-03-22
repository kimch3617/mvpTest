package com.example.mvptest.repository.local

import android.content.Context
import android.util.Log
import com.example.mvptest.data.User
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(context: Context) {

    private var executor: ExecutorService = Executors.newCachedThreadPool()
    private var userDao = AppDatabase.getInstance(context).userDao()

    init {
        Log.e("UserLocalDataSource", "init")
    }

    fun getUsers(loaded: (users: List<User>) -> Unit) {
        executor.execute {
            val users = userDao.getUsers()
            loaded(users)
        }
    }

    fun insertUser(user: User) {
        executor.execute {
            userDao.insertUser(user)
        }
    }

    fun containUsers(ids: List<Int>, loaded: (users: List<User>) -> Unit) {
        executor.execute {
            val users = userDao.containUsers(ids)
            loaded(users)
        }
    }

    fun deleteUser(user: User) {
        executor.execute {
            val users = userDao.deleteUser(user)
        }
    }

    fun finish() {
        executor.shutdown()
        AppDatabase.destroyInstance()
    }

}