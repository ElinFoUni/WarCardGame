package com.example.war.classes

import android.view.View

class Game {

    val deck = Deck()
    val humanPlayerCards = mutableListOf<Card>()
    val pcPlayerCards = mutableListOf<Card>()
    val temporaryCardPile = mutableListOf<Card>()

    //divides the deck in two then splits it between the players
    init {
        deck.cards.chunked(deck.cards.size / 2).let {
            humanPlayerCards.addAll(it[0])
            pcPlayerCards.addAll(it[1])
        }
    }

