package com.mendhie.weatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mendhie.weatherapp.data.models.WeatherConverter
import com.mendhie.weatherapp.data.models.WeatherForecast
import javax.inject.Inject

@Database(entities = [WeatherForecast::class], version = 1)
@TypeConverters(WeatherConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun WeatherDao(): WeatherDao

    class WeatherCallback @Inject constructor(): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }
    }
}