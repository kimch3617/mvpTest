package com.example.mvptest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(@PrimaryKey(autoGenerate = true) val id: Int, val login: String, val avatarUrl: String, val url: String, var isLike: Boolean) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is User)
            return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}