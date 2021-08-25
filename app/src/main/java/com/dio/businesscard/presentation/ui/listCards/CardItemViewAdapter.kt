package com.dio.businesscard.presentation.ui.listCards

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.dio.businesscard.databinding.CardItemBinding
import com.dio.businesscard.domain.model.Card
import com.dio.businesscard.presentation.extensions.setBackgroundColor
import com.dio.businesscard.presentation.extensions.setTextColor

class CardItemViewAdapter:
    ListAdapter<Card, CardItemViewAdapter.ViewHolder>(DiffCallback()) {

    var listenerShare: (View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: CardItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Card) {
            binding.tvNome.text = item.nome
            binding.tvTelefone.text = item.telefone
            binding.tvEmail.text = item.email
            binding.tvNomeEmpresa.text = item.empresa
            binding.setBackgroundColor(item.fundoPersonalizado)
            binding.setTextColor(item.fontePersonalizada)
            binding.cdContent.setOnClickListener {
                listenerShare(it)
            }
        }
    }

}

class DiffCallback : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Card, newItem: Card) =
        oldItem.id == newItem.id
}