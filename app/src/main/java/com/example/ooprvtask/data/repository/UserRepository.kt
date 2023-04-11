package com.example.ooprvtask.data.repository

import android.content.Context
import com.example.ooprvtask.data.models.User
import com.example.ooprvtask.data.source.usersRoom.UsersDataBase
import kotlinx.coroutines.flow.Flow

class UserRepository(context: Context) {

    private val db = UsersDataBase.getInstance(context)

     fun insertUser(user: User){
        db.myDao().insertUser(user)
    }

     fun getAllUsers(): Flow<List<User>>{
        return db.myDao().getAllUsers()
    }

}