package com.dio.businesscard.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dio.businesscard.domain.model.Card

@Entity
data class CardRoom(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val empresa: String,
    val telefone: String,
    val email: String,
    val fundoPersonalizado: Int,
    val fontePersonalizada: Int
) {

    fun toData(): Card {
        return Card(
            id = id,
            nome = nome,
            empresa = empresa,
            telefone = telefone,
            email = email,
            fundoPersonalizado = fundoPersonalizado,
            fontePersonalizada = fontePersonalizada
        )
    }
}