package com.example.war.classes

data class Card(val rank: Int, val suit: String) {

    //set cards to images in drawables folder:
    fun getCardImage(): String {
        val suitMap = mapOf(
            "hearts" to "hearts",
            "diamonds" to "diamonds",
            "spades" to "spades",
            "clover" to "clover"
        )
        return "${rank}_${suitMap[suit]}"
    }

}