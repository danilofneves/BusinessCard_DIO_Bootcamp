package com.dio.businesscard.domain.usecases

import com.dio.businesscard.domain.Either
import com.dio.businesscard.domain.model.Card

interface FetchCards {
    suspend operator fun invoke(): Either<List<Card>, Exception>
}