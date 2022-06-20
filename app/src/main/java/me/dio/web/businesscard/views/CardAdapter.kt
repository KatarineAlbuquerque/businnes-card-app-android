package me.dio.web.businesscard.views

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import me.dio.web.businesscard.R
import me.dio.web.businesscard.data.repository.CardRepository
import me.dio.web.businesscard.data.viewmodel.CardViewModel
import me.dio.web.businesscard.databinding.CardItemBinding
import me.dio.web.businesscard.model.Card

class CardAdapter(private var cards: List<Card>, private var context: Context) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private lateinit var cardViewModel:CardViewModel
    private lateinit var cardRepository: CardRepository

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var positionCard = cards[position]

        holder.binding.idItem.text = positionCard.id.toString()
        holder.binding.companyItem.text = positionCard.company.toString()
        holder.binding.nameItem.text = positionCard.name.toString()
        holder.binding.emailItem.text = positionCard.email.toString()
        holder.binding.phoneItem.text = positionCard.phone.toString()

        // Delete Item Id
        holder.binding.imageDelete.setOnClickListener{
            var id = holder.binding.idItem.text.toString()
            cardViewModel.removeCard(id)
            Toast.makeText(context, context.getString(R.string.remove_success), Toast.LENGTH_LONG).show()
            removeItem(positionCard)
        }
        // Share Data
        holder.binding.imageShare.setOnClickListener{
            var intent = Intent()
            intent.setAction(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TITLE, positionCard.name.toString())
            intent.setType("text/plain")
            context.startActivity(intent)
        }
    }
    // Remove Item List
    fun removeItem(cardPosition: Card) {
        notifyItemRemoved(cardPosition.id)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    class ViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {}

}