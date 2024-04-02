package com.kunal.learnandroid.roomDatabase

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Contact::class, User::class],
    version = 4,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = ContactDatabase.Migration2To3::class)
    ]
)
abstract class ContactDatabase : RoomDatabase() {

    abstract val dao: ContactDao

    @RenameColumn(tableName = "Contact", fromColumnName = "address", toColumnName = "addressNew")
    class Migration2To3 : AutoMigrationSpec

    companion object {
        val migration3To4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE IF NOT EXISTS user (name CHAR NOT NULL PRIMARY KEY)")
            }
        }
    }
}