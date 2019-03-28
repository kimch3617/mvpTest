package com.example.mvptest.repository.local

import androidx.room.*
import com.example.mvptest.data.User
import io.reactivex.Observable

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUsers(): Observable<List<User>>

    @Query("select * from user where id = :id")
    fun getUser(id: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

//    @Query("select * from user where id in (:ids)")
//    fun containUsers(ids: List<Int>): <List<User>

    @Query("select * from user where id in (:ids)")
    fun containUsers(ids: List<Int>): Observable<List<User>>

    @Delete
    fun deleteUser(user: User)
}