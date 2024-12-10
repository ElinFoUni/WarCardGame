package com.example.war

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainMenuActivity : AppCompatActivity() {

    //player name
    private lateinit var nameEditText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameEditText = findViewById(R.id.nameEditText)
        // returns the player name as a string
        fun getPlayerName() : String {
            val playerName = nameEditText.text.toString()
            return playerName
        }

        // Code below takes the app to the main class
        val startButton = findViewById<Button>(R.id.startGameButton)

        startButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            //sends the player name to MainActivity as an extra
            val playerName = getPlayerName()
            intent.putExtra("player_name", playerName)

            startActivity(intent)

        }
    }
}