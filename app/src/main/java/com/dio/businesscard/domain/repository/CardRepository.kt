package com.dio.businesscard.domain.repository

import com.dio.businesscard.data.model.CardRoom
import com.dio.businesscard.domain.Either
import com.dio.businesscard.domain.model.Card

interface CardRepository {
    suspend fun fetchCards(): Either<List<Card>, Exception>
    suspend fun saveCard(card: CardRoom): Either<Unit, Exception>
}