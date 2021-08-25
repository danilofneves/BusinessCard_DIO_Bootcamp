package com.dio.businesscard.data.repository

import com.dio.businesscard.data.local.LocalDataSource
import com.dio.businesscard.data.model.CardRoom
import com.dio.businesscard.domain.Either
import com.dio.businesscard.domain.model.Card
import com.dio.businesscard.domain.repository.CardRepository
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor (private val local: LocalDataSource):
    CardRepository {

    override suspend fun fetchCards(): Either<List<Card>, Exception> {
        return local.fetch()
    }

    override suspend fun saveCard(card: CardRoom) : Either<Unit, Exception> {
        return local.save(card)
    }
}