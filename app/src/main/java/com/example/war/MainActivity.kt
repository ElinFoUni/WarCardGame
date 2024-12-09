package com.example.war

import com.example.war.classes.Player
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.war.classes.Game

class MainActivity : AppCompatActivity() {

    lateinit var humanPlayer: Player
    lateinit var pcPlayer: Player
    private lateinit var game: Game

    //cards shown face up on screen
    lateinit var humanPlayerCardImage: ImageView
    lateinit var pcPlayerCardImage: ImageView

    //TODO create these in the xml
    //private lateinit var humanPlayerCountTextView: TextView
    //private lateinit var pcPlayerCountTextView: TextView
    //private lateinit var resultTextView: TextView

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

        //creates an object for the human player
        humanPlayer = Player(name = playerName)

        //PC player
        pcPlayer = Player(name = "Computer")

        game = Game()
        humanPlayerCardImage = findViewById(R.id.humanPlayerCardImage)
        pcPlayerCardImage = findViewById(R.id.pcPlayerCardImage)
        //TODO
        //humanPlayerCountTextView = findViewById(R.id.player1Count)
        //pcPlayerCountTextView = findViewById(R.id.player2Count)
        //resultTextView = findViewById(R.id.resultText)

        // What happens when you click on deal
        var dealButton = findViewById<ImageButton>(R.id.dealButton)
        dealButton.setOnClickListener{
            playRound()
        }
        //TODO add a greeting
        //TODO add win/lose states

    }

    // show cards on the screen
    fun playRound() {
        val (card1, card2) = game.playRound()

        if (card1 != null) {
            val resourceId1 = card1.getCardImage()
            humanPlayerCardImage.setImageResource(resourceId1)
        }
        if (card2 != null) {
            val resourceId2 = card2.getCardImage()
            pcPlayerCardImage.setImageResource(resourceId2)
        }
    }

}