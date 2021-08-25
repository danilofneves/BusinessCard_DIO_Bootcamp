package com.dio.businesscard.domain.di

import com.dio.businesscard.domain.repository.CardRepository
import com.dio.businesscard.domain.usecases.FetchCards
import com.dio.businesscard.domain.usecases.FetchCardsImpl
import com.dio.businesscard.domain.usecases.SaveCard
import com.dio.businesscard.domain.usecases.SaveCardImpl
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideFetchCards(repository: CardRepository): FetchCards = FetchCardsImpl(repository)

    @Provides
    fun provideSaveCards(repository: CardRepository): SaveCard = SaveCardImpl(repository)
}