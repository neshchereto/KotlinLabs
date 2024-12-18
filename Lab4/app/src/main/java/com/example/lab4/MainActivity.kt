package com.example.lab4

import android.content.Intent
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonToSecond = findViewById<Button>(R.id.button_to_second)
        buttonToSecond.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        val buttonToThird = findViewById<Button>(R.id.button_to_third)
        buttonToThird.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

        val amperageInput = findViewById<EditText>(R.id.amperage)
        val timeInput = findViewById<EditText>(R.id.time)
        val densityInput = findViewById<EditText>(R.id.density)
        val resultsView = findViewById<TextView>(R.id.results)

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener {
            val amperage = amperageInput.text.toString().toDoubleOrNull() ?: 0.0
            val time = timeInput.text.toString().toDoubleOrNull() ?: 0.0
            val density = densityInput.text.toString().toDoubleOrNull() ?: 0.0
            val result = sqrt(time) * amperage / density

            resultsView.text = Html.fromHtml("""
                Необхідне значення розміру перерізу s ≥ s<sub>min</sub>: <b>${"%.2f".format(result)} мм<sup>2</sup></b><br>
            """.trimIndent(),
                Html.FROM_HTML_MODE_LEGACY)
        }
    }
}
