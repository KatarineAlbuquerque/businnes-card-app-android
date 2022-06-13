package me.dio.web.businesscard.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.dio.web.businesscard.R
import me.dio.web.businesscard.adapter.CardAdapter
import me.dio.web.businesscard.data.viewmodel.CardViewModel
import me.dio.web.businesscard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cardAdapter: CardAdapter

    lateinit var cardViewModel: CardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewCards = binding.cardsRecyclerView
        recyclerViewCards.layoutManager = LinearLayoutManager(this)

        cardViewModel = ViewModelProvider(this).get(CardViewModel::class.java)

        // Get Cards List
        getCards(binding,recyclerViewCards)

        // Action Button Fab
        actionButtonFab(binding)
    }

    private fun getCards(binding: ActivityMainBinding, recyclerViewCards: RecyclerView) {
        cardViewModel.getAllCards.observe(this, Observer { cards ->
            cardAdapter = CardAdapter(cards, this)
            cardAdapter.notifyDataSetChanged()
            recyclerViewCards.adapter = cardAdapter

            // Add message No Cards
            if(!cards.isEmpty()) {
                binding.noCards.isGone = true
            }
            else {
                binding.noCards.isGone = false
                binding.noCards.text = getString(R.string.no_cards)
            }
        })
    }

    private fun actionButtonFab(binding: ActivityMainBinding) {
        binding.fab.setOnClickListener {
            var intent = Intent(this,AddCardActivity::class.java)
            startActivity(intent)
        }
    }
}
