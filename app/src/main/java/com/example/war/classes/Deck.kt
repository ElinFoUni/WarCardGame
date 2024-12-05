package com.example.war.classes

class Deck {
    //make a changable list of cards
    val cards = mutableListOf<Card>()

    init {
        populateDeck()
    }

    fun populateDeck() {
        val suits = listOf("Clover", "Diamonds", "Hearts", "Spades")
        for (suit in suits) {
            for (rank in 2..14) {
                cards.add(Card(rank, suit))
            }
        }
    }

    //shuffles the deck
    fun shuffleDeck() {
        cards.shuffle()
    }

    fun dealCard(): Card {
        return cards.removeAt(0)
    }

    // can be used to determine winner/show how many cards left in deck
    fun getCardCount(): Int {
        return cards.size
    }

}