package com.example.ants_todo.util

fun String.deleteExtraBlanks(): String {
    var wordsAfterModifications = ""
    val words = this.split(" ")
    words.forEach { word ->
        word.replace(" ", "")
    }
    val filteredWords = words.filter { it.isNotEmpty() }
    filteredWords.forEach { word ->
        wordsAfterModifications += if (filteredWords.last() != word) "$word " else word
    }
    return wordsAfterModifications
}