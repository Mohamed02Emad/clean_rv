package com.example.ooprvtask.ui.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ooprvtask.data.models.User
import com.example.ooprvtask.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private val _userList = MutableLiveData<ArrayList<User>>(ArrayList())
    val userList: LiveData<ArrayList<User>> = _userList

    fun validateName(userName: String): Boolean {
        return (userName.isNotEmpty())
    }

    private suspend fun addToList(user: User) = withContext(Dispatchers.Main) {
        _userList.value!!.add(user)
    }

    fun saveName(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            addToList(user)
            repository.insertUser(user)
        }
    }

    private suspend fun getCachedData(): Flow<List<User>> = withContext(Dispatchers.IO) {
        repository.getAllUsers()
    }

    suspend fun setInitial() {
        val list = getCachedData().first()
        _userList.value!!.addAll(list)
    }

}