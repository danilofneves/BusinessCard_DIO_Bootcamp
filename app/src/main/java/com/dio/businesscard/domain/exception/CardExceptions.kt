package com.dio.businesscard.domain.exception

sealed class CardException () : Exception(){

    object InvalidEmailException: CardException()
    object EmptyEmailException: CardException()
    object EmptyNomeException: CardException()
    object EmptyTelefoneException: CardException()
    object EmptyEmpresaException: CardException()

    object GeneralInsertException: CardException()
    object GeneralListException: CardException()
}