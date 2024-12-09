package com.example.war.classes

import android.view.View
import android.widget.ImageView
import com.example.war.R

data class Card(val rank: Int, val suit: String) {

    //set cards to images in drawables folder:
    fun getCardImage(): Int {
        println("Debug: rank=$rank, suit=$suit")
        return when (rank to suit) {
            14 to "spades" -> R.drawable.fourteen_spades
            14 to "hearts" -> R.drawable.fourteen_hearts
            14 to "diamonds" -> R.drawable.fourteen_diamonds
            14 to "clubs" -> R.drawable.fourteen_clover
            13 to "spades" -> R.drawable.thirteen_spades
            13 to "hearts" -> R.drawable.thirteen_hearts
            13 to "diamonds" -> R.drawable.thirteen_diamonds
            13 to "clubs" -> R.drawable.thirteen_clover
            12 to "spades" -> R.drawable.twelve_spades
            12 to "hearts" -> R.drawable.twelve_hearts
            12 to "diamonds" -> R.drawable.twelve_diamonds
            12 to "clubs" -> R.drawable.twelve_clover
            11 to "spades" -> R.drawable.eleven_spades
            11 to "hearts" -> R.drawable.eleven_hearts
            11 to "diamonds" -> R.drawable.eleven_diamonds
            11 to "clubs" -> R.drawable.eleven_clover
            10 to "spades" -> R.drawable.ten_spades
            10 to "hearts" -> R.drawable.ten_hearts
            10 to "diamonds" -> R.drawable.ten_diamonds
            10 to "clubs" -> R.drawable.ten_clover
            9 to "spades" -> R.drawable.nine_spades
            9 to "hearts" -> R.drawable.nine_hearts
            9 to "diamonds" -> R.drawable.nine_diamonds
            9 to "clubs" -> R.drawable.nine_clover
            8 to "spades" -> R.drawable.eight_spades
            8 to "hearts" -> R.drawable.eight_hearts
            8 to "diamonds" -> R.drawable.eight_diamonds
            8 to "clubs" -> R.drawable.eight_clover
            7 to "spades" -> R.drawable.seven_spades
            7 to "hearts" -> R.drawable.seven_hearts
            7 to "diamonds" -> R.drawable.seven_diamonds
            7 to "clubs" -> R.drawable.seven_clover
            6 to "spades" -> R.drawable.six_spades
            6 to "hearts" -> R.drawable.six_hearts
            6 to "diamonds" -> R.drawable.six_diamonds
            6 to "clubs" -> R.drawable.six_clover
            5 to "spades" -> R.drawable.five_spades
            5 to "hearts" -> R.drawable.five_hearts
            5 to "diamonds" -> R.drawable.five_diamonds
            5 to "clubs" -> R.drawable.five_clover
            4 to "spades" -> R.drawable.four_spades
            4 to "hearts" -> R.drawable.four_hearts
            4 to "diamonds" -> R.drawable.four_diamonds
            4 to "clubs" -> R.drawable.four_clover
            3 to "spades" -> R.drawable.three_spades
            3 to "hearts" -> R.drawable.three_hearts
            3 to "diamonds" -> R.drawable.three_diamonds
            3 to "clubs" -> R.drawable.three_clover
            2 to "spades" -> R.drawable.two_spades
            2 to "hearts" -> R.drawable.two_hearts
            2 to "diamonds" -> R.drawable.two_diamonds
            2 to "clubs" -> R.drawable.two_clover
            else -> {
                println("Debug: Fallback triggered for rank=$rank, suit=$suit")
                R.drawable.blue_back // Default fallback image
            }
        }
    }
}