package com.dio.businesscard.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dio.businesscard.data.model.CardRoom
import com.dio.businesscard.domain.model.Card

@Database(entities = [CardRoom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

  abstract fun cardDao(): CardDao

  companion object {
    private const val DATABASE = "cards_db"

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
      val tempInstance =
        INSTANCE
      if (tempInstance != null) {
        return tempInstance
      }
      synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          AppDatabase::class.java,
          DATABASE
        ).build()
        INSTANCE = instance
        return instance
      }
    }
  }
}
