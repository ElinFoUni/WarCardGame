package com.example.war

import android.content.Intent
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

    private lateinit var humanPlayer: Player
    private lateinit var pcPlayer: Player
    private lateinit var humanPlayerCount: TextView
    private lateinit var pcPlayerCount: TextView
    private lateinit var roundMessage: TextView
    private lateinit var game: Game

    //cards shown face up on screen
    private lateinit var humanPlayerCardImage: ImageView
    private lateinit var pcPlayerCardImage: ImageView

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
        val dealButton = findViewById<ImageButton>(R.id.dealButton)
        dealButton.setOnClickListener{
            play()
        }
    }

    // show cards on the screen
    private fun play() {
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

        when {
            card1 == null -> {
                goToLoseScreen()
            }
            card2 == null -> {
                goToWinScreen()
            }
            card1.rank > card2.rank -> {
                roundMessage.text = "The Computer wins this round!"
            }
            card1.rank < card2.rank -> {
                roundMessage.text = "You wins this round!"
            }
            else -> {
                roundMessage.text = "It's a tie!"
            }
        }

        // can be used to see what card is actually played, in the log, if the UI has issues
        Log.d("Card Debug", "Human player's card: $card1")
        Log.d("Card Debug", "AI player's card: $card2")
    }

    // Goes to the win screen
    private fun goToWinScreen() {
        val intent = Intent(this, WinScreenActivity::class.java)
        startActivity(intent)
        finish()    }

    // Goes to the lose screen
    private fun goToLoseScreen() {
        val intent = Intent(this, LoseScreenActivity::class.java)
        startActivity(intent)
        finish()
    }

    // Sets all cards to invisible
    private fun setCardInvisible() {
        setHumanCardInvisible(humanPlayerCardImage)
        setPCCardInvisible(pcPlayerCardImage)
    }

    // Set all cards as visible
    private fun setCardVisible(humanPlayerCardImage: View, pcPlayerCardImage: View) {
        setHumanCardVisible(humanPlayerCardImage)
        setPCCardVisible(pcPlayerCardImage)
    }

    private fun setHumanCardInvisible(humanPlayerCardImage: View) {
        humanPlayerCardImage.visibility = View.INVISIBLE
    }

    private fun setPCCardInvisible(pcPlayerCardImage: View) {
        pcPlayerCardImage.visibility = View.INVISIBLE
    }

    private fun setHumanCardVisible(humanPlayerCardImage: View) {
        humanPlayerCardImage.visibility = View.VISIBLE
    }

    private fun setPCCardVisible(pcPlayerCardImage: View) {
        pcPlayerCardImage.visibility = View.VISIBLE
    }
}



