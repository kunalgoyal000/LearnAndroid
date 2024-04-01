package com.kunal.learnandroid.roomDatabase

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Contact::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class ContactDatabase : RoomDatabase() {

    abstract val dao: ContactDao
}