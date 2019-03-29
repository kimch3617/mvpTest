package com.example.mvptest.repository.local

import android.content.Context
import android.util.Log
import com.example.mvptest.data.User
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(context: Context) {

//    @Inject lateinit var executor: ExecutorService
//    private var executor = Executors.newCachedThreadPool()
    private var userDao = AppDatabase.getInstance(context).userDao()

    init {
        Log.e("UserLocalDataSource", "init $this")
    }

//    fun getUsers(loaded: (users: List<User>) -> Unit) {
////        executor.execute {
////            val users = userDao.getUsers()
////            loaded(users)
////        }
//    }

    fun getUsers() = userDao.getUsers()

    fun insertUser(user: User) = userDao.insertUser(user)

//    fun containUsers(ids: List<Int>, loaded: (users: List<User>) -> Unit) {
////        executor.execute {
//            val users = userDao.containUsers(ids)
//            loaded(users)
////        }
//    }

    fun containUsers(ids: List<Int>) = userDao.containUsers(ids)

    fun deleteUser(user: User) = userDao.deleteUser(user)

    fun start() {
        Log.e("start", "start")
//        executor = Executors.newCachedThreadPool()
    }

    fun finish() {
        AppDatabase.destroyInstance()
    }

}