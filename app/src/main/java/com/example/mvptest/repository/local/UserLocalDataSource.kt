package com.example.mvptest.repository.local

import android.content.Context
import com.example.mvptest.data.User
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserLocalDataSource(context: Context) {
    private val mUserDao = AppDatabase.getInstance(context).userDao()
    private val mExecutor: ExecutorService = Executors.newCachedThreadPool()

    fun getUsers(loaded: (users: List<User>) -> Unit) {
        mExecutor.execute {
            val users = mUserDao.getUsers()
            loaded(users)
        }
    }

    fun insertUser(user: User) {
        mExecutor.execute {
            mUserDao.insertUser(user)
        }
    }

    fun containUsers(ids: List<Int>, loaded: (users: List<User>) -> Unit) {
        mExecutor.execute {
            val users = mUserDao.containUsers(ids)
            loaded(users)
        }
    }

    fun deleteUser(user: User) {
        mExecutor.execute {
            val users = mUserDao.deleteUser(user)
        }
    }

    fun finish() {
        mExecutor.shutdown()
        AppDatabase.destroyInstance()
    }
}