package com.example.lab1

import android.content.Intent
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

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

        val hydrogenInput = findViewById<EditText>(R.id.hydrogen)
        val carbonInput = findViewById<EditText>(R.id.carbon)
        val sulfurInput = findViewById<EditText>(R.id.sulfur)
        val nitrogenInput = findViewById<EditText>(R.id.nitrogen)
        val oxygenInput = findViewById<EditText>(R.id.oxygen)
        val ashInput = findViewById<EditText>(R.id.ash)
        val wetInput = findViewById<EditText>(R.id.wet)
        val resultsView = findViewById<TextView>(R.id.results)

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener {
            // Зчитування значень з полів
            val hydrogen = hydrogenInput.text.toString().toDoubleOrNull() ?: 0.0
            val carbon = carbonInput.text.toString().toDoubleOrNull() ?: 0.0
            val sulfur = sulfurInput.text.toString().toDoubleOrNull() ?: 0.0
            val nitrogen = nitrogenInput.text.toString().toDoubleOrNull() ?: 0.0
            val oxygen = oxygenInput.text.toString().toDoubleOrNull() ?: 0.0
            val ash = ashInput.text.toString().toDoubleOrNull() ?: 0.0
            val wet = wetInput.text.toString().toDoubleOrNull() ?: 0.0

            // Розрахунки
            val krs = 100 / (100 - wet)
            val krg = 100 / (100 - wet - ash)

            val hydrogenS = hydrogen * krs
            val carbonS = carbon * krs
            val sulfurS = sulfur * krs
            val nitrogenS = nitrogen * krs
            val oxygenS = oxygen * krs
            val ashS = ash * krs

            val hydrogenG = hydrogen * krg
            val carbonG = carbon * krg
            val sulfurG = sulfur * krg
            val nitrogenG = nitrogen * krg
            val oxygenG = oxygen * krg

            val q = (339 * carbon + 1030 * hydrogen + 108.8 * (oxygen - sulfur) - 25 * wet) / 1000
            val qs = (q + 0.025 * wet) * 100 / (100 - wet)
            val qg = (q + 0.025 * wet) * 100 / (100 - wet - ash)

            // Виведення результатів
            resultsView.text = """
                Водень (H): ${"%.2f".format(hydrogenS)} % (суха), ${"%.2f".format(hydrogenG)} % (горюча)
                Вуглець (C): ${"%.2f".format(carbonS)} % (суха), ${"%.2f".format(carbonG)} % (горюча)
                Сірка (S): ${"%.2f".format(sulfurS)} % (суха), ${"%.2f".format(sulfurG)} % (горюча)
                Азот (N): ${"%.2f".format(nitrogenS)} % (суха), ${"%.2f".format(nitrogenG)} % (горюча)
                Кисень (O): ${"%.2f".format(oxygenS)} % (суха), ${"%.2f".format(oxygenG)} % (горюча)
                Зола (A): ${"%.2f".format(ashS)} % (суха)
                
                Нижча теплота згорання (робоча): ${"%.2f".format(q)} МДж/кг
                Нижча теплота згорання (суха): ${"%.2f".format(qs)} МДж/кг
                Нижча теплота згорання (горюча): ${"%.2f".format(qg)} МДж/кг
            """.trimIndent()
        }
    }
}
