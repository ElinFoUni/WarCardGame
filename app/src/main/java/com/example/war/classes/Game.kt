package com.example.war.classes

class Game {

    val deck = Deck()
    val humanPlayerCards = mutableListOf<Card>()
    val pcPlayerCards = mutableListOf<Card>()

    //divides the deck in two then splits it between the players
    init {
        deck.cards.chunked(deck.cards.size / 2).let {
            humanPlayerCards.addAll(it[0])
            pcPlayerCards.addAll(it[1])
        }
    }

    //returns the two played cards
    fun playRound(): Pair<Card?, Card?> {
        //takes out the first cards from both card piles
        val p1Card = humanPlayerCards.removeFirstOrNull()
        val p2Card = pcPlayerCards.removeFirstOrNull()
        return p1Card to p2Card
    }

    fun determineRoundWinner(humanCard: Card?, pcCard: Card?): Int {
        return when {
            humanCard == null -> 3 //if human has no cards left (should take you to loss screen)
            pcCard == null -> 4 // Should take you to Win screen
            humanCard.rank > pcCard.rank -> 1 // human wins the round
            humanCard.rank < pcCard.rank -> 2
            else -> 0 // Draw if the ranks are equal
        }
    }

    // humanPlayerCardImage.visibility = View.INVISIBLE

    fun checkGameWinState(): Int? {
        return when {
            humanPlayerCards.isEmpty() -> 2 // does it need to be same value as above?
            pcPlayerCards.isEmpty() -> 1
            else -> null
        }

    }

}