package com.dio.businesscard.presentation.ui.listCards

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dio.businesscard.R
import com.dio.businesscard.core.ViewModelFactory
import com.dio.businesscard.databinding.FragmentCardListBinding
import com.dio.businesscard.domain.exception.CardException
import com.dio.businesscard.domain.model.Card
import com.dio.businesscard.presentation.extensions.observe
import com.dio.businesscard.presentation.extensions.toast
import com.dio.businesscard.presentation.helper.Image
import com.dio.businesscard.presentation.viewmodel.CardViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CardListFragment : Fragment() {

    private lateinit var binding: FragmentCardListBinding

    lateinit var cardViewModel: CardViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val adapter by lazy { CardItemViewAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardListBinding.inflate(inflater, container, false)

        cardViewModel = viewModelFactory.create(CardViewModel::class.java)
        cardViewModel.getCards()

        binding.rvCards.adapter = adapter

        observe(cardViewModel.cardsLiveData, ::handleListCards)
        observe(cardViewModel.cardException, ::handleErrorCard)

        listeners()

        return binding.root
    }

    private fun listeners(){
        val context: Context = this.context ?: return
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_CardListFragment_to_CardSaveFragment)
        }
        adapter.listenerShare = { card ->
            Image.share(context, card)
        }
    }

    private fun handleListCards(cards: List<Card>){
        adapter.submitList(cards)
    }

    private fun handleErrorCard(error: CardException){
        if(error is CardException.GeneralListException){
            toast(getString(R.string.error_lista_cartao))
        }
    }

}