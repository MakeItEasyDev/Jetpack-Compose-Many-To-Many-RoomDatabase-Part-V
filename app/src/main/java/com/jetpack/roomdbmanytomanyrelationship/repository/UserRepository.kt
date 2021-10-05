package com.jetpack.roomdbmanytomanyrelationship.repository

import com.jetpack.roomdbmanytomanyrelationship.dao.UserDao
import com.jetpack.roomdbmanytomanyrelationship.entity.*

class UserRepository(private val userDao: UserDao) {

    suspend fun addUser(item: List<User>) {
        userDao.insertUser(item = item)
    }

    suspend fun addLibrary(item: List<Library>) {
        userDao.insertLibrary(item = item)
    }

    suspend fun addUserLibrary(item: List<UserLibraryCrossRef>) {
        userDao.insertUserLibrary(item = item)
    }

    fun getUserWithLibrary(userId: Int): List<UserWithLibrary> {
        return userDao.getUserWithLibrary(userId)
    }

    fun getLibraryWithUser(id: Int): List<LibraryWithUser> {
        return userDao.getLibraryWithUser(id)
    }
}











