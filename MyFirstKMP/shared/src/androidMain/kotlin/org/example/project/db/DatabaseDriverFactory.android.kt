package org.example.project.db
import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver


actual class DatabaseDriverFactory(private var context: Context) {

    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = MyFirstKMPDatabase.Schema,
            context = context,
            name = "MyFirstKMP.database.db"
        )
}