package com.kunal.learnandroid.roomDatabase

import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val DB_NAME = "test"

@RunWith(AndroidJUnit4::class)
class ContactMigrationTest {

    @get:Rule
    val helper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        ContactDatabase::class.java,
        listOf(ContactDatabase.Migration2To3()),
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    fun migration1To2_containsCorrectData() {
        var db = helper.createDatabase(DB_NAME, 1).apply {
            execSQL("INSERT INTO contact VALUES('Kunal', 'Goyal', '1233456789','0')")
            close()
        }

        db = helper.runMigrationsAndValidate(DB_NAME, 2, true)

        db.query("SELECT * FROM contact").apply {
            assertThat(moveToFirst()).isTrue()
            assertThat(getString(getColumnIndex("address"))).isEqualTo("")

        }
    }

    @Test
    fun testAllMigrations() {
        helper.createDatabase(DB_NAME, 1).apply { close() }

        Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            ContactDatabase::class.java,
            DB_NAME
        ).addMigrations(ContactDatabase.migration3To4).build().apply {
            openHelper.writableDatabase.close()
        }
    }
}