package com.jetpack.roomdbmanytomanyrelationship.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jetpack.roomdbmanytomanyrelationship.dao.UserDao
import com.jetpack.roomdbmanytomanyrelationship.entity.Library
import com.jetpack.roomdbmanytomanyrelationship.entity.User
import com.jetpack.roomdbmanytomanyrelationship.entity.UserLibraryCrossRef

@Database(entities = [User::class, Library::class, UserLibraryCrossRef::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            val userInstance = INSTANCE
            if (userInstance != null) {
                return userInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).allowMainThreadQueries().build()

                INSTANCE = instance
                return instance
            }
        }
    }
}














