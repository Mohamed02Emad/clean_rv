package com.example.ooprvtask.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val username: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)