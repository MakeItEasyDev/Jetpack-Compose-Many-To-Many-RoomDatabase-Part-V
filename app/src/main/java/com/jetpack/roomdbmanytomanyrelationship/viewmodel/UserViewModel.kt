package com.jetpack.roomdbmanytomanyrelationship.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.jetpack.roomdbmanytomanyrelationship.database.UserDatabase
import com.jetpack.roomdbmanytomanyrelationship.entity.Library
import com.jetpack.roomdbmanytomanyrelationship.entity.User
import com.jetpack.roomdbmanytomanyrelationship.entity.UserLibraryCrossRef
import com.jetpack.roomdbmanytomanyrelationship.entity.UserWithLibrary
import com.jetpack.roomdbmanytomanyrelationship.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val _readAllData = MutableLiveData<List<UserWithLibrary>>()
    var readAllData: LiveData<List<UserWithLibrary>> = _readAllData
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
    }

    fun getUser(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _readAllData.postValue(repository.getUserWithLibrary(userId = userId))
        }
    }

    fun addUser(item: List<User>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(item = item)
        }
    }

    fun addLibrary(item: List<Library>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLibrary(item = item)
        }
    }

    fun addUserLibrary(item: List<UserLibraryCrossRef>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUserLibrary(item = item)
        }
    }
}

class UserViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(application) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}


















