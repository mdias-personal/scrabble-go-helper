/* (C)2024 */
package com.mdias.scrabblegohelper

import android.annotation.SuppressLint
import android.content.Context
import mdias.scrabblegohelper.R
import kotlin.streams.toList

class Dictionary(
    context: Context,
) {
    private val dict: HashSet<String> = initDict(context)

    @SuppressLint("NewApi")
    private fun initDict(context: Context): HashSet<String> {
        val reader = context.resources.openRawResource(R.raw.enable_dict).bufferedReader()
        return reader.lines().toList().toHashSet()
    }

    fun findScoredWords(
        shelf: String,
        otherLetters: String,
    ): ArrayList<ScoredWord> {
        val permutations: List<String> = getValidPermutations(shelf + otherLetters).distinct().toList()

        val scoredWords: ArrayList<ScoredWord> =
            ArrayList(
                permutations
                    .map { word: String -> ScoredWord(word) }
                    .sortedByDescending { scoredWord: ScoredWord -> scoredWord.score }
                    .toList(),
            )

        return if (scoredWords.size <= 10) scoredWords else ArrayList(scoredWords.subList(0, 10))
    }

    private fun getValidPermutations(shelf: String): List<String> {
        val result: ArrayList<String> = ArrayList()
        getPerm(shelf, "", result)
        return result
    }

    private fun getPerm(
        word: String,
        perm: String,
        result: ArrayList<String>,
    ) {
        if (this.dict.contains(perm)) {
            result.add(perm)
        }
        if (word.isEmpty()) {
            return
        } else {
            for (i in word.indices) {
                val c: Char = word[i]
                val rest: String = word.substring(0, i) + word.substring(i + 1)
                getPerm(rest, perm + c, result)
            }
        }
    }
}
