package com.dio.businesscard.data.local

import com.dio.businesscard.data.model.CardRoom
import com.dio.businesscard.domain.Either
import com.dio.businesscard.domain.model.Card


interface LocalDataSource {
    suspend fun fetch(): Either<List<Card>, Exception>
    suspend fun save(card: CardRoom): Either<Unit, Exception>
}
