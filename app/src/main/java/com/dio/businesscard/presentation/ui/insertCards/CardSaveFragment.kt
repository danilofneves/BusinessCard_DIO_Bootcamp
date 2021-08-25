package com.dio.businesscard.presentation.ui.insertCards

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.dio.businesscard.R
import com.dio.businesscard.core.ViewModelFactory
import com.dio.businesscard.databinding.FragmentCardSaveBinding
import com.dio.businesscard.domain.exception.CardException
import com.dio.businesscard.domain.model.Card
import com.dio.businesscard.presentation.extensions.observe
import com.dio.businesscard.presentation.extensions.toast
import com.dio.businesscard.presentation.viewmodel.CardViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CardSaveFragment : Fragment() {

    private lateinit var binding: FragmentCardSaveBinding

    lateinit var cardViewModel: CardViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cardViewModel = viewModelFactory.create(CardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardSaveBinding.inflate(inflater, container, false)

        observe(cardViewModel.success, ::handleSaveCard)
        observe(cardViewModel.cardException, ::handleErrorCard)

        listeners()
        return binding.root
    }

    private fun listeners(){
        binding.btnConfirm.setOnClickListener {
            val card = Card(
                nome = binding.tilNome.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                fundoPersonalizado = binding.tilFundoCor.getColor(),
                fontePersonalizada = binding.tilTextCor.getColor(),
            )
            cardViewModel.addCard(card)
        }
        binding.btnClose.setOnClickListener {
            navController.popBackStack()
        }
    }

    private fun handleSaveCard(card: Unit){
        toast(getString(R.string.sucessfull_msg))
        navController.popBackStack()
    }

    private fun handleErrorCard(error: CardException){
        val message = when(error){
            CardException.EmptyEmailException -> getString(R.string.error_email_vazio)
            CardException.EmptyNomeException -> getString(R.string.error_nome_vazio)
            CardException.EmptyTelefoneException -> getString(R.string.error_telefone_vazio)
            CardException.EmptyEmpresaException -> getString(R.string.error_empresa_vazio)
            CardException.InvalidEmailException -> getString(R.string.error_email_invalido)
            else -> getString(R.string.error_cadastro_cartao)
        }
        toast(message)
    }

}