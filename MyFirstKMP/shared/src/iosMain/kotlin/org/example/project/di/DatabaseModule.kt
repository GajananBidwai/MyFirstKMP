package org.example.project.di

import app.cash.sqldelight.db.SqlDriver
import org.example.project.db.DatabaseDriverFactory
import org.example.project.db.MyFirstKMPDatabase
import org.koin.dsl.module

val databaseModule = module {

    single<SqlDriver> { DatabaseDriverFactory().createDriver() }

    single<MyFirstKMPDatabase> { MyFirstKMPDatabase(get()) }
}