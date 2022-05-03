package com.mdias.scrabblegohelper

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import mdias.scrabblegohelper.R


class MainActivity : AppCompatActivity() {
    private var currentShelf: String = ""
    private val maxShelfLength: Int = 7;
    private val maxExtraLength: Int = 3;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val shelfField = findViewById<EditText>(R.id.shelf_letters)

        // unpack bundle
        val bundle: Bundle? = this.intent.extras
        if (bundle != null) {
            this.currentShelf = bundle.getString("current_shelf").toString()
            shelfField.setText(this.currentShelf)
            displayTiles(
                this.currentShelf,
                ::getShelfImageViewId,
                this.maxShelfLength,
            )
        }

        // add listeners
        findViewById<Button>(R.id.button).setOnClickListener { buttonClicked() }
        setTextChangeListener(
            shelfField,
            this.maxShelfLength,
            ::getShelfImageViewId
        )
        setTextChangeListener(
            findViewById<EditText>(R.id.other_letters),
            this.maxExtraLength,
            ::getExtraImageViewId
        )
    }

    private fun setTextChangeListener(
        editText: EditText,
        maxLength: Int,
        findImageViewFunc: (Int) -> Int
    ) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                displayTiles(s.toString().lowercase(), findImageViewFunc, maxLength)
            }
        })
    }

    private fun displayTiles(letters: String, findImageViewFunc: (Int) -> Int, maxLength: Int) {
        for (i in 0 until maxLength) {
            val tile: ImageView = findViewById<ImageView>(findImageViewFunc.invoke(i))
            if (i + 1 <= letters.length) {
                tile.setImageResource(getImageId(letters[i]))
                tile.visibility = View.VISIBLE
            } else {
                tile.visibility = View.INVISIBLE
            }
        }
    }

    private fun getShelfImageViewId(tileToShow: Int): Int {
        return when (tileToShow) {
            0 -> R.id.tile1
            1 -> R.id.tile2
            2 -> R.id.tile3
            3 -> R.id.tile4
            4 -> R.id.tile5
            5 -> R.id.tile6
            6 -> R.id.tile7
            else -> -1
        }
    }

    private fun getExtraImageViewId(tileToShow: Int): Int {
        return when (tileToShow) {
            0 -> R.id.extra_tile1
            1 -> R.id.extra_tile2
            2 -> R.id.extra_tile3
            else -> -1
        }
    }

    private fun getImageId(c: Char): Int {
        return when (c) {
            'a' -> R.drawable.a
            'b' -> R.drawable.b
            'c' -> R.drawable.c
            'd' -> R.drawable.d
            'e' -> R.drawable.e
            'f' -> R.drawable.f
            'g' -> R.drawable.g
            'h' -> R.drawable.h
            'i' -> R.drawable.i
            'j' -> R.drawable.j
            'k' -> R.drawable.k
            'l' -> R.drawable.l
            'm' -> R.drawable.m
            'n' -> R.drawable.n
            'o' -> R.drawable.o
            'p' -> R.drawable.p
            'q' -> R.drawable.q
            'r' -> R.drawable.r
            's' -> R.drawable.s
            't' -> R.drawable.t
            'u' -> R.drawable.u
            'v' -> R.drawable.v
            'w' -> R.drawable.w
            'x' -> R.drawable.x
            'y' -> R.drawable.y
            'z' -> R.drawable.z
            else -> -1
        }
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

