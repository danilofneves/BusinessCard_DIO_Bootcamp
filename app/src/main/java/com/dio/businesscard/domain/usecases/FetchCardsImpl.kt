package com.dio.businesscard.domain.usecases

import com.dio.businesscard.domain.Either
import com.dio.businesscard.domain.model.Card
import com.dio.businesscard.domain.repository.CardRepository
import javax.inject.Inject

class FetchCardsImpl  @Inject constructor(
    private val repository: CardRepository
) : FetchCards {
    override suspend operator fun invoke(): Either<List<Card>, Exception> = repository.fetchCards()
}