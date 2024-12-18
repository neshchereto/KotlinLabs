package com.example.lab5

import android.content.Intent
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val buttonToFirst = findViewById<Button>(R.id.button_to_first)
        buttonToFirst.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val frequencyInput = findViewById<EditText>(R.id.frequency)
        val timeInput = findViewById<EditText>(R.id.time)
        val powerInput = findViewById<EditText>(R.id.power)
        val time2Input = findViewById<EditText>(R.id.time2)
        val t1Input = findViewById<EditText>(R.id.t_1)
        val t2Input = findViewById<EditText>(R.id.t_2)

        val resultsView = findViewById<TextView>(R.id.results)

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener {
            val frequency = frequencyInput.text.toString().toDoubleOrNull() ?: 0.0
            val time      = timeInput.text.toString().toDoubleOrNull() ?: 0.0
            val power     = powerInput.text.toString().toDoubleOrNull() ?: 0.0
            val time2     = time2Input.text.toString().toDoubleOrNull() ?: 0.0
            val t1        = t1Input.text.toString().toDoubleOrNull() ?: 0.0
            val t2        = t2Input.text.toString().toDoubleOrNull() ?: 0.0

            val ma = frequency * time * power * time2
            val mp = 0.004 * power * time2
            val result = t1 * ma + t2 * mp

            resultsView.text = Html.fromHtml("""
                Математичне сподівання збитків від переривання електропостачання: <b>${"%.2f".format(result)}</b> грн.
            """.trimIndent(),
                Html.FROM_HTML_MODE_LEGACY)
        }
    }
}
