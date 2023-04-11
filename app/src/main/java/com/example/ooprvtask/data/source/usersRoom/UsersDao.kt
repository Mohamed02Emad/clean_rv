package com.example.ooprvtask.data.source.usersRoom

import androidx.room.*
import com.example.ooprvtask.data.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Query("SELECT * FROM User ")
    fun getAllUsers(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

}