package com.dio.businesscard.domain.usecases

import com.dio.businesscard.data.model.CardRoom
import com.dio.businesscard.domain.Either
import com.dio.businesscard.domain.exception.CardException
import com.dio.businesscard.domain.extensions.isValidEmail
import com.dio.businesscard.domain.model.Card
import com.dio.businesscard.domain.repository.CardRepository
import javax.inject.Inject

class SaveCardImpl @Inject constructor(
    private val repository: CardRepository
) : SaveCard {

    override suspend operator fun invoke(card: Card): Either<Unit, Exception> {
        return when {
            card.nome.isEmpty() -> Either.Failure(CardException.EmptyNomeException)
            card.telefone.isEmpty() -> Either.Failure(CardException.EmptyTelefoneException)
            card.email.isEmpty() -> Either.Failure(CardException.EmptyEmailException)
            card.email.isValidEmail() -> Either.Failure(CardException.InvalidEmailException)
            card.empresa.isEmpty() -> Either.Failure(CardException.EmptyEmpresaException)
            else -> repository.saveCard(
                CardRoom(
                    id = 0,
                    nome = card.nome,
                    empresa = card.empresa,
                    telefone = card.telefone,
                    email = card.email,
                    fundoPersonalizado = card.fundoPersonalizado,
                    fontePersonalizada = card.fontePersonalizada
                )
            )
        }
    }
}