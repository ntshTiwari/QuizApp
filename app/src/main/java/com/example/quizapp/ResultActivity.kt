package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val usernameTextView = findViewById<TextView>(R.id.usernameTextView)
        val resultStringTextView = findViewById<TextView>(R.id.resultStringTextView)
        val finishButton = findViewById<Button>(R.id.finishButton)

        val username = intent.getStringExtra(Constants.USER_NAME)
        val number_of_correct_answers = intent.getIntExtra(Constants.NUMBER_OF_CORRECT_ANSWERS, 0)

        usernameTextView.text = username
        resultStringTextView.text = "Your score is $number_of_correct_answers out of 10"

        finishButton.setOnClickListener {
            restartGame()
        }
    }

    private fun restartGame() {
        val mainActivity = Intent(this, MainActivity::class.java)
        startActivity(mainActivity)
        finish()
    }
}