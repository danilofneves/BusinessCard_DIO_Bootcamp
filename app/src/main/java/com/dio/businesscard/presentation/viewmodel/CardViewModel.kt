package com.dio.businesscard.presentation.viewmodel

import androidx.lifecycle.*
import com.dio.businesscard.domain.Either
import com.dio.businesscard.domain.exception.CardException
import com.dio.businesscard.domain.model.Card
import com.dio.businesscard.domain.usecases.FetchCards
import com.dio.businesscard.domain.usecases.SaveCard
import kotlinx.coroutines.launch
import javax.inject.Inject

class CardViewModel @Inject constructor(
    private val fetchCard: FetchCards,
    private val saveCard: SaveCard
):ViewModel(){

    private val _cardsLiveData: MutableLiveData<List<Card>> = MutableLiveData()
    val cardsLiveData: LiveData<List<Card>> get() = _cardsLiveData

    private val _success = MutableLiveData<Unit>()
    val success: LiveData<Unit> = _success

    private val _cardException = MutableLiveData<CardException>()
    val cardException: LiveData<CardException> = _cardException

    fun getCards(){
        viewModelScope.launch {
            when(val result = fetchCard()
            ){
                is Either.Success ->  _cardsLiveData.postValue(result.data)
                is Either.Failure -> _cardException.postValue(result.cause as CardException)
            }
        }
    }

    fun addCard(card: Card){
        viewModelScope.launch {
            when(val result = saveCard(card)
            ){
                is Either.Success -> _success.postValue(Unit)
                is Either.Failure -> _cardException.postValue(result.cause as CardException)
            }
        }
    }

}