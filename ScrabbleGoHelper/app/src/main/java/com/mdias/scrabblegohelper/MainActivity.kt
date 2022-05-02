package com.mdias.scrabblegohelper

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import mdias.scrabblegohelper.R

class MainActivity : AppCompatActivity() {
    private var currentShelf: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle:Bundle? = this.intent.extras
        if (bundle != null) {
            this.currentShelf = bundle.getString("current_shelf").toString()
            findViewById<EditText>(R.id.shelf_letters).setText(this.currentShelf)
        }

        findViewById<Button>(R.id.button).setOnClickListener { buttonClicked() }
    }

    private fun buttonClicked() {

        this.currentShelf = findViewById<EditText>(R.id.shelf_letters).text.toString()
        val wordList: ArrayList<ScoredWord> = Dictionary(this.baseContext).findScoredWords(
            this.currentShelf,
            findViewById<EditText>(R.id.other_letters).text.toString(),
        )

        intent = Intent(this, ResultsActivity::class.java)
        intent.putExtra("word_list", wordList)
        intent.putExtra("current_shelf", this.currentShelf)
        startActivity(intent)
    }
}

