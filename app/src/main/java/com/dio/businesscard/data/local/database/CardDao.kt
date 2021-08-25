package com.dio.businesscard.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dio.businesscard.data.model.CardRoom

@Dao
interface CardDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insertCard(card: CardRoom)

  @Query("SELECT * FROM CardRoom")
  suspend fun getCardList(): List<CardRoom>
}
