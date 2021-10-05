package com.jetpack.roomdbmanytomanyrelationship.dao

import androidx.room.*
import com.jetpack.roomdbmanytomanyrelationship.entity.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(item: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLibrary(item: List<Library>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserLibrary(item: List<UserLibraryCrossRef>)

    @Transaction
    @Query("SELECT * FROM User WHERE userId = :userId")
    fun getUserWithLibrary(userId: Int): List<UserWithLibrary>

    @Transaction
    @Query("SELECT * FROM Library WHERE id = :id")
    fun getLibraryWithUser(id: Int): List<LibraryWithUser>
}