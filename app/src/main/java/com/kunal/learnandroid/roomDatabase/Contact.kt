package com.kunal.learnandroid.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    @ColumnInfo(name = "address", defaultValue = "")
    val address: String = "",
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
