package com.spartabasic.basic

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.spartabasic.basic.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random





class MainActivity : AppCompatActivity() {
    private var job: Job? = null

    private lateinit var button: Button
    private lateinit var randomTextView: TextView
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        button = findViewById(R.id.clickButton)
        randomTextView = findViewById(R.id.textViewRandom)
        textView = findViewById(R.id.spartaTextView)

        setupButton()
        setRandomValueBetweenOneToHundred()
        setJobAndLaunch()

    }




    private fun setupButton() {
        button.setOnClickListener {
            job?.cancel()
            checkAnswerAndShowToast()
        }
    }

    private fun setRandomValueBetweenOneToHundred() {
        val randomValue = Random.nextInt(100) + 1
        randomTextView.text = randomValue.toString()
    }

    private fun setJobAndLaunch() {

        job = lifecycleScope.launch {
            for (i in 1..100) {
                if (isActive) {
                    textView.text = i.toString()
                    delay(500)
                }
            }
        }
    }


    private fun checkAnswerAndShowToast() {
        val textView = textView.text.toString()
        val randomTextView = randomTextView.text.toString()

        if (textView == randomTextView){
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
        }
    }
}