package com.dio.businesscard.presentation.extensions

import com.dio.businesscard.databinding.CardItemBinding

fun CardItemBinding.setBackgroundColor(color:Int){
    this.cdContent.setBackgroundColor(color)
}

fun CardItemBinding.setTextColor(color:Int){
    this.tvEmail.setTextColor(color)
    this.tvNome.setTextColor(color)
    this.tvNomeEmpresa.setTextColor(color)
    this.tvTelefone.setTextColor(color)
}