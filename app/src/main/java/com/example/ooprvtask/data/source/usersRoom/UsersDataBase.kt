package com.example.ooprvtask.data.source.usersRoom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ooprvtask.data.models.User


@Database(entities = [User::class], version = 1)
abstract class UsersDataBase : RoomDatabase() {

    abstract fun myDao(): UsersDao

    companion object {
        private var instancee: UsersDataBase? = null

        private const val DB_NAME = "users_db"

        fun getInstance(context: Context): UsersDataBase {

            return instancee ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context,
                    UsersDataBase::class.java,
                    DB_NAME
                ).build()
                instancee = instance
                instance
            }
        }
    }
}