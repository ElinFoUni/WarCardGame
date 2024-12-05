package com.example.war

import com.example.war.classes.Player
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var humanPlayer: Player
    lateinit var pcPlayer: Player

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
        humanPlayer = Player(name = playerName, cardsLeft = 26)

        //PC player
        pcPlayer = Player(name = "Computer", cardsLeft = 26)

        //TODO add a greeting


    }
}