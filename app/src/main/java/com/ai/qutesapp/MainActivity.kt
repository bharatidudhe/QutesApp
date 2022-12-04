package com.ai.qutesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    val textQuote : TextView
    get() = findViewById(R.id.quoteText)

    val textQuoteAuthor : TextView
        get() = findViewById(R.id.quoteAuthor)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(viewModel.getQuote())

    }

    fun setQuote(quotes: Quotes){
        textQuote.text = quotes.text
        textQuoteAuthor.text = quotes.author
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,viewModel.getQuote().text)
        startActivity(intent)

    }

    fun onNext(view: View) {
        setQuote(viewModel.nextQuote())
    }
    fun onPrevious(view: View) {
        setQuote(viewModel.previousQuote())
    }
}