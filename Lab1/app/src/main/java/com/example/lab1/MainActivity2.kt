package com.example.lab1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2) // Вказуємо макет другої сторінки

        val buttonToSecond = findViewById<Button>(R.id.button_to_first)
        buttonToSecond.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val hydrogenInput = findViewById<EditText>(R.id.hydrogen)
        val carbonInput   = findViewById<EditText>(R.id.carbon)
        val sulfurInput   = findViewById<EditText>(R.id.sulfur)
        val oxygenInput   = findViewById<EditText>(R.id.oxygen)
        val ashInput      = findViewById<EditText>(R.id.ash)
        val wetInput      = findViewById<EditText>(R.id.wet)
        val vanadiumInput = findViewById<EditText>(R.id.vanadium)
        val qInput        = findViewById<EditText>(R.id.q)
        val resultsView   = findViewById<TextView>(R.id.results)

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener {
            // Зчитування значень з полів
            val hydrogen = hydrogenInput.text.toString().toDoubleOrNull() ?: 0.0
            val carbon = carbonInput.text.toString().toDoubleOrNull() ?: 0.0
            val sulfur = sulfurInput.text.toString().toDoubleOrNull() ?: 0.0
            val oxygen = oxygenInput.text.toString().toDoubleOrNull() ?: 0.0
            val ash = ashInput.text.toString().toDoubleOrNull() ?: 0.0
            val wet = wetInput.text.toString().toDoubleOrNull() ?: 0.0
            val vanadium = vanadiumInput.text.toString().toDoubleOrNull() ?: 0.0
            val q = qInput.text.toString().toDoubleOrNull() ?: 0.0

            val krs = (100 - wet) / 100
            val krg = (100 - wet - ash) / 100

            val hydrogenS = hydrogen * krg
            val carbonS = carbon * krg
            val sulfurS = sulfur * krg
            val oxygenS = oxygen * krg
            val ashS = ash * krs
            val vanadiumS = vanadium * krs

            val result = q * (100 - wet - ash) / 100 - 0.025 * wet

            resultsView.text = """
                Склад робочої маси мазуту
                
                Водень (H): ${"%.2f".format(hydrogenS)} %
                Вуглець (C): ${"%.2f".format(carbonS)} %
                Сірка (S): ${"%.2f".format(sulfurS)} %
                Кисень (O): ${"%.2f".format(oxygenS)} %
                Зола (A): ${"%.2f".format(ashS)} %
                Ванадій (V): ${"%.2f".format(vanadiumS)} %
                
                Нижча теплота згорання робочої маси: ${"%.2f".format(result)} МДж/кг
            """.trimIndent()
        }
    }
}
