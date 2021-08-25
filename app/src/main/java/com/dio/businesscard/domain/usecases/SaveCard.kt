package com.dio.businesscard.domain.usecases

import com.dio.businesscard.domain.Either
import com.dio.businesscard.domain.model.Card

interface SaveCard {
    suspend operator fun invoke(card: Card) : Either<Unit, Exception>
}