package com.example.war.classes

class Deck {
    private val suits = listOf("hearts", "diamonds", "spades", "clubs")
    private val ranks = (2..14) //14 is Ace, 13 is King etc

    //makes a changable list of cards
    val cards = mutableListOf<Card>()

    //adds cards to the deck and shuffles
    init {
        for (suit in suits) {
            for (rank in ranks) {
                cards.add(Card(rank, suit))
            }
        }
        cards.shuffle()
    }

}