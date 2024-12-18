package com.example.lab3

import android.content.Intent
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val powerInput = findViewById<EditText>(R.id.power)
        val tInput = findViewById<EditText>(R.id.t)

        val resultsView = findViewById<TextView>(R.id.results)

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener {
            val power = powerInput.text.toString().toDoubleOrNull() ?: 0.0
            val t = tInput.text.toString().toDoubleOrNull() ?: 0.0

            val loss = 0.8 * power * 24 * t - 0.2 * power * 24 * t
            val profit = 0.68 * power * 24 * t - 0.32 * power * 24 * t

            resultsView.text = Html.fromHtml("""
                При СКВ в 1 МВт електростанція втрачатиме: <b>${"%.2f".format(loss)} тис. грн.</b><br><br>
	            При СКВ в 0.25 МВт електростанція зароблятиме: <b>${"%.2f".format(profit)} тис. грн.</b>
            """.trimIndent(),
                Html.FROM_HTML_MODE_LEGACY)
        }
    }
}
