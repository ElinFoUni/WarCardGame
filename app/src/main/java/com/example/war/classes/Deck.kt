package com.example.war.classes

class Deck {
    private val suits = listOf("hearts", "diamonds", "spades", "clover")
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

    fun dealCard(): Card? {
        return if (cards.isNotEmpty()) cards.removeAt(0) else null
    }

    //Simple version of the draw, should be replaced by a "war" function in actual game.
    // Doesn't work atm
    // fun addCardsToBottom(drawedCards: List<Card>) {
    //    cards.addAll(newCards)
    //}


    // can be used to determine winner/show how many cards left in deck
    fun getCardCount(): Int {
        return cards.size
    }

}