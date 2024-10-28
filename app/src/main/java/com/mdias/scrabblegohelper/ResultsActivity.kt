package com.mdias.scrabblegohelper

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import mdias.scrabblegohelper.R

class ResultsActivity : AppCompatActivity() {
    private var currentShelf: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results)

        findViewById<Button>(R.id.button).setOnClickListener {
            this.intent = Intent(this, MainActivity::class.java)
            intent.putExtra("current_shelf", this.currentShelf)
            startActivity(this.intent)
        }

        val bundle:Bundle = this.intent.extras!!
        this.currentShelf = bundle.getString("current_shelf").toString()
        val scoredWords : ArrayList<ScoredWord> = bundle.get("word_list") as ArrayList<ScoredWord>

        val listView = findViewById<ListView>(R.id.results_list)
        val listItems = scoredWords.map { it.word + " " + it.score.toString() }
        val adapter = ArrayAdapter(this, R.layout.list_item, listItems)
        listView.adapter = adapter
        listView.setOnItemClickListener {parent, view, position, id ->
            val word = scoredWords.get(position).word;
            val intent = Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, word + " definition");
            startActivity(intent);
        };
    }
}

