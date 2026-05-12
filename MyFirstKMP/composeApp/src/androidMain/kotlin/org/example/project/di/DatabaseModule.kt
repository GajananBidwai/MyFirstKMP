package org.example.project.di

import app.cash.sqldelight.db.SqlDriver
import org.example.project.db.DatabaseDriverFactory
import org.example.project.db.MyFirstKMPDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }

    single<MyFirstKMPDatabase> { MyFirstKMPDatabase(get()) }
}