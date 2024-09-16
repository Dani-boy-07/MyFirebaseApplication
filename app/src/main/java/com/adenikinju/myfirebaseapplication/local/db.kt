package com.adenikinju.myfirebaseapplication.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [movies::class], version = 1)
abstract class db : RoomDatabase()  {
    abstract fun moviesDao(): MoviesDao
}