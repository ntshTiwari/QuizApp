package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn = findViewById<Button>(R.id.startBtn)
        val nameEditText = findViewById<EditText>(R.id.nameEditText)

        startBtn.setOnClickListener {
            if(nameEditText.text.isEmpty()){
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show()
            } else {
                /// this is how we get an intent
                val quizQuestionIntent =  Intent(this, QuizQuestionsActivity::class.java)
                /// this will start the activity
                /// or move us to that screen
                startActivity(quizQuestionIntent)
                /// this will close the current activity or screen
                /// so, we will not be able to go back from here
                finish()
            }
        }
    }
}