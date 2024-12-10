package com.example.war.classes

import android.view.View

class Game {

    val deck = Deck()
    val humanPlayerCards = mutableListOf<Card>()
    val pcPlayerCards = mutableListOf<Card>()
    val temporaryCardPile = mutableListOf<Card>() // holds the cards in play

    //divides the deck into two chunks then splits it between the players
    init {
        deck.cards.chunked(deck.cards.size / 2).let {
            humanPlayerCards.addAll(it[0])
            pcPlayerCards.addAll(it[1])
        }
    }

    //returns the two played cards, not sure if "pair" is a good way to do it
    fun playRound(): Pair<Card?, Card?> {
        // Ensure neither player is out of cards to prevent errors.
        if (humanPlayerCards.isEmpty() || pcPlayerCards.isEmpty()) {
            return null to null
        }
        // Picks out the top card of each deck
        val humanCard = humanPlayerCards.removeFirstOrNull()
        val pcCard = pcPlayerCards.removeFirstOrNull()

        // adds the two cards to the temporary list (if their deck is not empty)
        if (humanCard != null) temporaryCardPile.add(humanCard)
        if (pcCard != null) temporaryCardPile.add(pcCard)

        // Checks to see what the outcome of the round is
        return when {
            // checks if either player is out of cards
            humanCard == null -> null to pcCard // Human player loses
            pcCard == null -> humanCard to null // PC player loses

            // Human wins the round
            humanCard.rank > pcCard.rank -> {
                // calls the winCards function to add the cards to the bottom of the deck
                collectWinnings(humanPlayerCards)
                humanCard to pcCard
            }

            // PC wins the round
            humanCard.rank < pcCard.rank -> {
                collectWinnings(pcPlayerCards)
                humanCard to pcCard
            }
            else -> {
                // In case of tie, calls the ResolveTie function
                tieBreaker()
                humanCard to pcCard
            }
        }

    }

    private fun tieBreaker() {
        // since a tie means that players could return cards to their deck we need a new temp
        // deck for both players
        val tempHumanPile = mutableListOf<Card>()
        val tempPCPile = mutableListOf<Card>()

        while (true) {
            // should handle a scenario where a player runs out of cards without a round winner
            if (humanPlayerCards.isEmpty() || pcPlayerCards.isEmpty()) {
                humanPlayerCards.addAll(tempHumanPile)
                pcPlayerCards.addAll(tempPCPile)
                return
            }
            // for now this handles the tie gameplay automatically
            val humanCard = humanPlayerCards.removeFirstOrNull()
            val pcCard = pcPlayerCards.removeFirstOrNull()

            if (humanCard != null) tempHumanPile.add(humanCard)
            if (pcCard != null) tempPCPile.add(pcCard)

            when {
                // checks if human has won the tie breaker
                humanCard != null && pcCard != null && humanCard.rank > pcCard.rank -> {
                    temporaryCardPile.addAll(tempHumanPile)
                    temporaryCardPile.addAll(tempPCPile)
                    collectWinnings(humanPlayerCards) // Human gets all cards in the temp pile
                    return
                }

                // checks if the computer has won the tie breaker
                humanCard != null && pcCard != null && humanCard.rank < pcCard.rank -> {
                    temporaryCardPile.addAll(tempHumanPile)
                    temporaryCardPile.addAll(tempPCPile)
                    collectWinnings(pcPlayerCards) // PC gets all cards
                    return
                }
                else -> { continue }
            }
        }
    }

    // appends the elements of the temporary deck to the bottom of the round winner's deck
    private fun collectWinnings(roundWinner: MutableList<Card>) {
        roundWinner.addAll(temporaryCardPile)
        temporaryCardPile.clear()
    }

}

