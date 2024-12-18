package com.example.lab4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity3 : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val buttonToFirst = findViewById<Button>(R.id.button_to_first)
        buttonToFirst.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val buttonToSecond = findViewById<Button>(R.id.button_to_second)
        buttonToSecond.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        val voltageInput = findViewById<EditText>(R.id.voltage)
        val nomPowerInput = findViewById<EditText>(R.id.nom_power)
        val resultsView = findViewById<TextView>(R.id.results)

        val calculateButton = findViewById<Button>(R.id.calculateButton)

        calculateButton.setOnClickListener {
            val voltage = voltageInput.text.toString().toDoubleOrNull() ?: 0.0
            val nomPower = nomPowerInput.text.toString().toDoubleOrNull() ?: 0.0

            val xt = 11.1 * voltage.pow(2) / (100 * nomPower)
            var xsh = xt + 24.02
            var zsh = sqrt(10.65.pow(2) + xsh.pow(2))

            var xshMin = xt + 65.68
            var zshMin = sqrt(34.88.pow(2) + xshMin.pow(2))

            val i13 = voltage * 1000 / (sqrt(3.0) * zsh)
            val i12 = i13 * sqrt(3.0) / 2
            val i13Min = voltage * 1000 / (sqrt(3.0) * zshMin)
            val i12Min = i13Min * sqrt(3.0) / 2



            val k = 11.0.pow(2) / 115.0.pow(2)
            xsh *= k.toDouble()

            zsh  = sqrt((10.65 * k.toDouble()).pow(2) + xsh.pow(2))
            xshMin *= k.toDouble()
            zshMin = sqrt((34.88 * k.toDouble()).pow(2) + xshMin.pow(2))

            val i23 = 11 * 1000 / (sqrt(3.0) * zsh)
            val i22 = i23 * sqrt(3.0) / 2
            val i23Min = 11 * 1000/(sqrt(3.0) * zshMin)
            val i22Min = i23Min * sqrt(3.0)/2


            val rSum = 10.65 * k.toDouble() + 7.91
            val xSum = xsh + 4.49
            val zSum = sqrt(rSum.pow(2) + xSum.pow(2))
            val rSumMin = 34.88 * k.toDouble() + 7.91
            val xSumMin = xshMin + 4.49
            val zSumMin = sqrt(rSumMin.pow(2) + xSumMin.pow(2))

            val i33 = 11 * 1000 / (sqrt(3.0) * zSum)
            val i32 = i33 * sqrt(3.0) / 2
            val i33Min = 11 * 1000 / (sqrt(3.0) * zSumMin)
            val i32Min = i33Min * sqrt(3.0) / 2

            resultsView.text = Html.fromHtml("""                
                Струм трифазного КЗ (I<sub>1-3</sub>)<br>
                Нормальний: <b>${"%.2f".format(i13)} A</b> | Мінімальний: <b>${"%.2f".format(i13Min)} A</b>
                <br><br>
                Струм двофазного КЗ (I<sub>1-2</sub>)<br>
                Нормальний: <b>${"%.2f".format(i12)} A</b> | Мінімальний: <b>${"%.2f".format(i12Min)} A</b>
                <br><br>
                Дійсний струм трифазного КЗ (I<sub>2-3</sub>)<br>
                Нормальний: <b>${"%.2f".format(i23)} A</b> | Мінімальний: <b>${"%.2f".format(i23Min)} A</b>
                <br><br>
                Дійсний струм двофазного КЗ (I<sub>2-2</sub>)<br>
                Нормальний: <b>${"%.2f".format(i22)} A</b> | Мінімальний: <b>${"%.2f".format(i22Min)} A</b>
                <br><br>
                Струм короткого замикання трифазного КЗ (I<sub>3-3</sub>)<br>
                Нормальний: <b>${"%.2f".format(i33)} A</b> | Мінімальний: <b>${"%.2f".format(i33Min)} A</b>
                <br><br>
                Струм короткого замикання двофазного КЗ (I<sub>3-2</sub>)<br>
                Нормальний: <b>${"%.2f".format(i32)} A</b> | Мінімальний: <b>${"%.2f".format(i32Min)} A</b>
            """.trimIndent(),
                Html.FROM_HTML_MODE_LEGACY)
        }
    }
}