package com.dio.businesscard.data.local

import com.dio.businesscard.data.local.database.CardDao
import com.dio.businesscard.data.model.CardRoom
import com.dio.businesscard.domain.Either
import com.dio.businesscard.domain.exception.CardException
import com.dio.businesscard.domain.model.Card
import javax.inject.Inject


class LocalDataSourceImpl @Inject constructor(private val dao: CardDao) : LocalDataSource {

    override suspend fun fetch(): Either<List<Card>, Exception> {
        return try{
            val result = dao.getCardList()
            Either.Success(result.map {
                it.toData()
            })
        }
        catch (e:Exception){
            Either.Failure(CardException.GeneralListException)
        }
    }

    override suspend fun save(card: CardRoom): Either<Unit, Exception> {
        return try{
            dao.insertCard(card)
            Either.Success(Unit)
        }
        catch (e:Exception){
            Either.Failure(CardException.GeneralInsertException)
        }
    }
}
