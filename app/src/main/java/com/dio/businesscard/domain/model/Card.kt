package com.dio.businesscard.domain.model

data class Card(
    val id: Int = 0,
    val nome: String,
    val empresa: String,
    val telefone: String,
    val email: String,
    val fundoPersonalizado: Int,
    val fontePersonalizada: Int
)