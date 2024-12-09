package com.example.war

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.war.classes.Card
import com.example.war.classes.Game
import com.example.war.classes.Player

class MainActivity : AppCompatActivity() {

    lateinit var humanPlayer: Player
    lateinit var pcPlayer: Player
    private lateinit var humanPlayerCount: TextView
    private lateinit var pcPlayerCount: TextView
    private lateinit var roundMessage: TextView
    private lateinit var game: Game

    //cards shown face up on screen
    lateinit var humanPlayerCardImage: ImageView
    lateinit var pcPlayerCardImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //gets player name from the main menu
        val playerName = intent.getStringExtra("player_name") ?: "Bob" //Defaults to Bob

        humanPlayerCount = findViewById(R.id.humanPlayerCountTextView)
        pcPlayerCount = findViewById(R.id.pcPlayerCountTextView)
        roundMessage = findViewById(R.id.roundMessageTextView)

        humanPlayerCardImage = findViewById(R.id.humanPlayerCardImage)
        pcPlayerCardImage = findViewById(R.id.pcPlayerCardImage)

        //creates an object for the human player
        humanPlayer = Player(name = playerName)

        //PC player
        pcPlayer = Player(name = "Computer")
        setCardInvisible()
        game = Game()

        // What happens when you click on deal
        var dealButton = findViewById<ImageButton>(R.id.dealButton)
        dealButton.setOnClickListener{
            playRound()
        }
        //TODO add a greeting
        //TODO add win/lose statesg
    }

    // show cards on the screen
    fun playRound() {
        setCardVisible(humanPlayerCardImage, pcPlayerCardImage)
        val (card1, card2) = game.playRound()

        if (card1 != null) {
            val resourceId1 = card1.getCardImage()
            humanPlayerCardImage.setImageResource(resourceId1)
        }
        if (card2 != null) {
            val resourceId2 = card2.getCardImage()
            pcPlayerCardImage.setImageResource(resourceId2)
        }
        humanPlayerCount.text = "${game.humanPlayerCards.size}"
        pcPlayerCount.text = "${game.humanPlayerCards.size}"

        Log.d("Card Debug", "Human player's card: $card1")
        Log.d("Card Debug", "AI player's card: $card2")
    }

    // Sets all cards to invisible
    fun setCardInvisible() {
        setHumanCardInvisible(humanPlayerCardImage)
        setPCCardInvisible(pcPlayerCardImage)
    }

    // Set all cards as visible
    fun setCardVisible(humanPlayerCardImage: View, pcPlayerCardImage: View) {
        setHumanCardVisible(humanPlayerCardImage)
        setPCCardVisible(pcPlayerCardImage)
    }

    fun setHumanCardInvisible(humanPlayerCardImage: View) {
        humanPlayerCardImage.visibility = View.INVISIBLE
    }

    fun setPCCardInvisible(pcPlayerCardImage: View) {
        pcPlayerCardImage.visibility = View.INVISIBLE
    }

    fun setHumanCardVisible(humanPlayerCardImage: View) {
        humanPlayerCardImage.visibility = View.VISIBLE
    }

    fun setPCCardVisible(pcPlayerCardImage: View) {
        pcPlayerCardImage.visibility = View.VISIBLE
    }

    //returns the two played cards
    fun playRound(): Pair<Card?, Card?> {
        // Ensure neither player is out of cards to prevent errors
        if (humanPlayerCards.isEmpty() || pcPlayerCards.isEmpty()) {
            return null to null
        }
        val humanCard = humanPlayerCards.removeFirstOrNull()
        val pcCard = pcPlayerCards.removeFirstOrNull()

        // adds cards to the temp pilegg
        if (humanCard != null) temporaryCardPile.add(humanCard)
        if (pcCard != null) temporaryCardPile.add(pcCard)

        return when {
            humanCard == null -> null to pcCard // Human player loses
            pcCard == null -> humanCard to null // PC player loses
            humanCard.rank > pcCard.rank -> {
                // Human wins this round
                collectCards(humanPlayerCards)
                humanCard to pcCard
            }
            humanCard.rank < pcCard.rank -> {
                // PC wins this round
                collectCards(pcPlayerCards)
                humanCard to pcCard
            }
            else -> {
                // Tie: Recurse with the next cards
                resolveTie()
                humanCard to pcCard
            }
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

            fugn collecgt gggggggggggggg


        }

    }



}