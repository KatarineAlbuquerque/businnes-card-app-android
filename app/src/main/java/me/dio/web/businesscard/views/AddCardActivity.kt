package me.dio.web.businesscard.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import me.dio.web.businesscard.R
import me.dio.web.businesscard.data.viewmodel.CardViewModel
import me.dio.web.businesscard.databinding.ActivityAddCardBinding
import me.dio.web.businesscard.model.Card

class AddCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCardBinding
    private lateinit var cardViewModel: CardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Buttons Close and Add
        actionsButtons(binding)

        cardViewModel = ViewModelProvider(this).get(CardViewModel::class.java)
    }

    private fun insertData() {
        val company = binding.textInputLayoutCompany.editText?.text.toString()
        val name = binding.textInputLayoutName.editText?.text.toString()
        val email = binding.textInputLayoutEmail.editText?.text.toString()
        val phone = binding.textInputLayoutPhone.editText?.text.toString()

        if(inputChecked(company, name, email, phone)) {
            val card = Card(0,company,name,email,phone)
            // Insert Card
            cardViewModel.insertCard(card)
            Toast.makeText(this, getString(R.string.success), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
        }
    }

    private fun inputChecked(company: String, name: String, email: String, phone: String): Boolean {
        return !(TextUtils.isEmpty(company) && (TextUtils.isEmpty(name) && (TextUtils.isEmpty(email) && (TextUtils.isEmpty(phone)))))
    }

    private fun actionsButtons(binding: ActivityAddCardBinding) {
        binding.imageClose.setOnClickListener{
            finish()
        }

        binding.buttonAdd.setOnClickListener{
            insertData()
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}