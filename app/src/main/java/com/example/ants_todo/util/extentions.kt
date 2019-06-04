package com.example.ants_todo.util

fun String.deleteExtraBlanks(): String {
    val words = this.split(" ")
    words.forEach { word ->
        word.replace(" ", "")
    }
    var wordsAfterModifications = ""
    words.forEach {
        if (it.isNotEmpty() && words.last() != it) {
            wordsAfterModifications += "$it "
        }
        if (it.isNotEmpty() && words.last() == it) {
            wordsAfterModifications += it
        }
    }
    return wordsAfterModifications
}