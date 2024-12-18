package com.example.lab4

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

        val buttonToFirst= findViewById<Button>(R.id.button_to_first)
        buttonToFirst.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val buttonToThird = findViewById<Button>(R.id.button_to_third)
        buttonToThird.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

        val voltageInput = findViewById<EditText>(R.id.voltage)
        val powerInput = findViewById<EditText>(R.id.power)
        val nomPowerInput = findViewById<EditText>(R.id.nom_power)
        val basicPowerInput = findViewById<EditText>(R.id.basic_power)
        val resultsView = findViewById<TextView>(R.id.results)

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener {
            val voltage = voltageInput.text.toString().toDoubleOrNull() ?: 0.0
            val power = powerInput.text.toString().toDoubleOrNull() ?: 0.0
            val nomPower = nomPowerInput.text.toString().toDoubleOrNull() ?: 0.0
            val basicPower = basicPowerInput.text.toString().toDoubleOrNull() ?: 0.0

            val xs = voltage.pow(2) / power;
            val xt = voltage.pow(3) / (100 * nomPower)
            val xsum = xs + xt
            val startAmperage = voltage / (sqrt(3.0) * xsum)
            val basicAmperage = basicPower / (sqrt(3.0) * voltage)

            resultsView.text = Html.fromHtml("""
                <p><strong>Опори елементів заступної схеми:</strong>
                <br>X<sub>с</sub> = ${"%.2f".format(xs)} Ом</p>
                X<sub>т</sub> = ${"%.2f".format(xt)} Ом</p>
                <p><strong>Сумарний опір для точки К1:</strong>
                <br>X<sub>Σ</sub> = ${"%.2f".format(xsum)} Ом</p>
                <p><strong>Початкове діюче значення струму трифазного КЗ:</strong>
                <br>I<sub>п0</sub> = ${"%.2f".format(startAmperage)} кА</p>
                <p><strong>Базисне значення струму трифазного КЗ:</strong>" 
                <br>I<sub>п0</sub> = ${"%.2f".format(basicAmperage)} кА</p>
            """.trimIndent(),
                Html.FROM_HTML_MODE_LEGACY)
        }
    }
}
